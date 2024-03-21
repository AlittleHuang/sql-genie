package io.github.genie.sql.data.access;

import io.github.genie.sql.api.Column;
import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.Operator;
import io.github.genie.sql.api.Query;
import io.github.genie.sql.api.Query.Select;
import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.api.Update;
import io.github.genie.sql.api.Updater;
import io.github.genie.sql.builder.Expressions;
import io.github.genie.sql.builder.TypeCastUtil;
import io.github.genie.sql.builder.TypedExpressions;
import io.github.genie.sql.builder.meta.Metamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class BaseAccessImpl<T, ID> extends AccessFacade<T> implements BaseAccess<T> {
    protected Query query;
    protected Update update;
    protected Metamodel metamodel;
    protected Class<T> entityType;
    protected Select<T> select;
    protected Column idColumn;
    protected Updater<T> updater;

    protected BaseAccessImpl() {
    }

    public BaseAccessImpl(Class<T> entityType) {
        this.entityType = entityType;
    }

    static <T, ID> Access<T, ID> access(Class<T> entityType) {
        return new AccessImpl<>(entityType);
    }

    @Autowired
    protected void setEntityType(Query query, Update update, Metamodel metamodel) {
        this.query = query;
        this.update = update;
        this.metamodel = metamodel;
        this.entityType = resolveEntityType();
        this.select = query.from(entityType);
        this.updater = update.getUpdater(entityType);
        this.idColumn = Expressions.column(metamodel.getEntity(entityType).id().name());
    }

    protected Class<T> resolveEntityType() {
        if (this.entityType != null) {
            return this.entityType;
        }
        ResolvableType type = ResolvableType.forClass(getClass()).as(BaseAccessImpl.class);
        Class<?> entityType = type.resolveGeneric(0);
        return TypeCastUtil.cast(entityType);
    }

    public T get(ID id) {
        Expression operate = Expressions.operate(idColumn, Operator.EQ, Expressions.of(id));
        TypedExpression<T, Boolean> predicate = TypedExpressions.of(operate);
        return where(predicate).getSingle();
    }

    public List<T> getAll(Iterable<? extends ID> ids) {
        List<Expression> idsExpression = StreamSupport.stream(ids.spliterator(), false)
                .map(Expressions::of).collect(Collectors.toList());
        if (idsExpression.isEmpty()) {
            return Collections.emptyList();
        }
        Expression operate = Expressions.operate(idColumn, Operator.IN, idsExpression);
        TypedExpression<T, Boolean> predicate = TypedExpressions.of(operate);
        return where(predicate).getList();
    }

    public Map<ID, T> getMap(Iterable<? extends ID> ids) {
        List<T> entities = getAll(ids);
        return entities.stream()
                .collect(Collectors.toMap(
                        it -> TypeCastUtil.unsafeCast(metamodel.getEntity(entityType).id().get(it)),
                        Function.identity()
                ));
    }

    @Override
    protected Select<T> select() {
        return select;
    }

    @Override
    protected Updater<T> updater() {
        return this.updater;
    }

    static class AccessImpl<T, ID> extends BaseAccessImpl<T, ID> implements Access<T, ID> {
        public AccessImpl(Class<T> entityType) {
            super(entityType);
        }
    }
}