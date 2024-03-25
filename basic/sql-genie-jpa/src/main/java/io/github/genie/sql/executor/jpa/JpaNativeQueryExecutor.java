package io.github.genie.sql.executor.jpa;

import io.github.genie.sql.api.Column;
import io.github.genie.sql.api.QueryStructure;
import io.github.genie.sql.api.Selection;
import io.github.genie.sql.api.Selection.EntitySelected;
import io.github.genie.sql.api.Selection.MultiSelected;
import io.github.genie.sql.api.Selection.ProjectionSelected;
import io.github.genie.sql.api.Selection.SingleSelected;
import io.github.genie.sql.builder.AbstractQueryExecutor;
import io.github.genie.sql.builder.Tuples;
import io.github.genie.sql.builder.TypeCastUtil;
import io.github.genie.sql.builder.converter.TypeConverter;
import io.github.genie.sql.builder.meta.Attribute;
import io.github.genie.sql.builder.meta.EntityType;
import io.github.genie.sql.builder.meta.Metamodel;
import io.github.genie.sql.builder.meta.Type;
import io.github.genie.sql.builder.reflect.InstanceConstructor;
import io.github.genie.sql.builder.reflect.ReflectUtil;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor.PreparedSql;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor.QuerySqlBuilder;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JpaNativeQueryExecutor implements AbstractQueryExecutor {
    private final QuerySqlBuilder sqlBuilder;
    private final EntityManager entityManager;
    private final Metamodel metamodel;
    private final TypeConverter typeConverter;

    public JpaNativeQueryExecutor(QuerySqlBuilder sqlBuilder, EntityManager entityManager, Metamodel metamodel, TypeConverter typeConverter) {
        this.sqlBuilder = sqlBuilder;
        this.entityManager = entityManager;
        this.metamodel = metamodel;
        this.typeConverter = typeConverter;
    }

    @Override
    public <T> List<T> getList(@NotNull QueryStructure queryStructure) {
        return queryByNativeSql(queryStructure);
    }

    private <T> List<T> queryByNativeSql(@NotNull QueryStructure queryStructure) {
        PreparedSql preparedSql = sqlBuilder.build(queryStructure, metamodel);
        jakarta.persistence.Query query = entityManager.createNativeQuery(preparedSql.sql());
        int position = 0;
        for (Object arg : preparedSql.args()) {
            query.setParameter(++position, arg);
        }
        List<?> list = TypeCastUtil.cast(query.getResultList());

        return resolve(list, preparedSql.selected(), queryStructure);
    }

    protected <T> List<T> resolve(
            List<?> resultSet,
            List<? extends Attribute> selected,
            QueryStructure structure) {
        List<T> result = new ArrayList<>(resultSet.size());
        if (resultSet.isEmpty()) {
            return result;
        }
        Selection select = structure.select();
        int columnsCount = asArray(resultSet.get(0)).length;

        if (select instanceof MultiSelected multiSelected) {
            if (multiSelected.expressions().size() != columnsCount) {
                throw new IllegalStateException();
            }
            List<Class<?>> types = multiSelected.expressions().stream()
                    .map(expression -> {
                        if (expression instanceof Column) {
                            Type t = metamodel.getEntity(structure.from().type());
                            //noinspection PatternVariableCanBeUsed
                            Column column = (Column) expression;
                            for (String s : column) {
                                t = ((EntityType) t).getAttribute(s);
                            }
                            return t.javaType();
                        }
                        return Object.class;
                    })
                    .collect(Collectors.toList());
            for (Object r : resultSet) {
                Object[] row = asArray(r);
                if (typeConverter != null) {
                    for (int i = 0; i < selected.size(); i++) {
                        Class<?> attribute = types.get(i);
                        row[i] = typeConverter.convert(row[i], attribute);
                    }
                }
                result.add(TypeCastUtil.unsafeCast(Tuples.of(row)));
            }
        } else if (select instanceof SingleSelected) {
            if (1 != columnsCount) {
                throw new IllegalStateException();
            }
            for (Object r : resultSet) {
                Object[] row = asArray(r);
                result.add(TypeCastUtil.unsafeCast(row[0]));
            }
        } else {
            if (selected.size() != columnsCount) {
                throw new IllegalStateException();
            }
            Class<?> resultType = getResultType(structure);
            InstanceConstructor extractor = ReflectUtil.getRowInstanceConstructor(selected, resultType);
            for (Object r : resultSet) {
                Object[] array = asArray(r);
                if (typeConverter != null) {
                    for (int i = 0; i < selected.size(); i++) {
                        Attribute attribute = selected.get(i);
                        array[i] = typeConverter.convert(array[i], attribute.javaType());
                    }
                }
                T row = TypeCastUtil.unsafeCast(extractor.newInstance(array));
                result.add(row);
            }
        }
        return result;
    }

    private Object[] asArray(Object r) {
        if (r instanceof Object[]) {
            return (Object[]) r;
        }
        return new Object[]{r};
    }

    private static Class<?> getResultType(QueryStructure structure) {
        Selection select = structure.select();
        Class<?> resultType;
        if (select instanceof EntitySelected) {
            resultType = structure.from().type();
        } else if (select instanceof ProjectionSelected) {
            resultType = select.resultType();
        } else {
            throw new IllegalStateException();
        }
        return resultType;
    }
}
