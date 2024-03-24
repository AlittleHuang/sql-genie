package io.github.genie.sql.executor.jpa;

import io.github.genie.sql.api.QueryStructure;
import io.github.genie.sql.api.Selection;
import io.github.genie.sql.api.Selection.EntitySelected;
import io.github.genie.sql.api.Selection.MultiSelected;
import io.github.genie.sql.api.Selection.ProjectionSelected;
import io.github.genie.sql.api.Selection.SingleSelected;
import io.github.genie.sql.builder.AbstractQueryExecutor;
import io.github.genie.sql.builder.Tuples;
import io.github.genie.sql.builder.TypeCastUtil;
import io.github.genie.sql.builder.meta.Attribute;
import io.github.genie.sql.builder.meta.Metamodel;
import io.github.genie.sql.builder.reflect.InstanceConstructor;
import io.github.genie.sql.builder.reflect.ReflectUtil;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor.PreparedSql;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor.QuerySqlBuilder;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JpaNativeQueryExecutor implements AbstractQueryExecutor {
    private final QuerySqlBuilder sqlBuilder;
    private final EntityManager entityManager;
    private final Metamodel metamodel;

    public JpaNativeQueryExecutor(QuerySqlBuilder sqlBuilder, EntityManager entityManager, Metamodel metamodel) {
        this.sqlBuilder = sqlBuilder;
        this.entityManager = entityManager;
        this.metamodel = metamodel;
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
        Selection select = structure.select();
        if (resultSet.isEmpty()) {
            return new ArrayList<>();
        }
        int columnsCount = asArray(resultSet.get(0)).length;

        if (select instanceof MultiSelected multiSelected) {
            if (multiSelected.expressions().size() != columnsCount) {
                throw new IllegalStateException();
            }
            for (Object r : resultSet) {
                Object[] row = asArray(r);
                if (multiSelected.expressions().size() != columnsCount) {
                    throw new IllegalStateException();
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
                T row = TypeCastUtil.unsafeCast(extractor.newInstance(asArray(r)));
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
