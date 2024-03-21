package io.github.genie.sql.builder;

import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.Operation;
import io.github.genie.sql.api.Path;
import io.github.genie.sql.api.Path.BooleanPath;
import io.github.genie.sql.api.Path.ComparablePath;
import io.github.genie.sql.api.Path.NumberPath;
import io.github.genie.sql.api.Path.StringPath;
import io.github.genie.sql.api.Query.PredicateBuilder;
import io.github.genie.sql.api.Root;
import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.api.TypedExpression.BooleanExpression;
import io.github.genie.sql.api.TypedExpression.BooleanPathExpression;
import io.github.genie.sql.api.TypedExpression.ComparablePathExpression;
import io.github.genie.sql.api.TypedExpression.JoinPathExpression;
import io.github.genie.sql.api.TypedExpression.NumberPathExpression;
import io.github.genie.sql.api.TypedExpression.StringPathExpression;
import io.github.genie.sql.builder.TypedExpressionImpl.BooleanExpressionImpl;
import io.github.genie.sql.builder.TypedExpressionImpl.ComparableExpressionImpl;
import io.github.genie.sql.builder.TypedExpressionImpl.NumberExpressionImpl;
import io.github.genie.sql.builder.TypedExpressionImpl.StringExpressionImpl;

public class RootImpl<T> implements Root<T> {

    private static final RootImpl<?> INSTANCE = new RootImpl<>();

    public static <T> Root<T> of() {
        return TypeCastUtil.cast(INSTANCE);
    }

    protected RootImpl() {
    }

    @Override
    public BooleanExpression<T> whereIf(boolean predicate, PredicateBuilder<T> predicateBuilder) {
        Expression holder = predicate ? predicateBuilder.build(this).expression() : Expressions.TRUE;
        return TypedExpressions.ofBoolean(holder);
    }

    @Override
    public <U> TypedExpression<T, U> of(U value) {
        return TypedExpressions.of(value);
    }

    @Override
    public <U> JoinPathExpression<T, U> join(Path<T, U> path) {
        return new TypedExpressionImpl<>((Operation) null, Expressions.of(path));
    }

    @Override
    public <U> JoinPathExpression<T, U> get(Path<T, U> path) {
        return new TypedExpressionImpl<>((Operation) null, Expressions.of(path));
    }

    @Override
    public StringPathExpression<T> get(StringPath<T> path) {
        return string(path);
    }

    @Override
    public <U extends Number & Comparable<U>> NumberPathExpression<T, U> get(NumberPath<T, U> path) {
        return number(path);
    }

    @Override
    public <U extends Comparable<U>> ComparablePathExpression<T, U> get(ComparablePath<T, U> path) {
        return comparable(path);
    }

    @Override
    public BooleanPathExpression<T> get(BooleanPath<T> path) {
        return bool(path);
    }

    @Override
    public StringPathExpression<T> string(Path<T, String> path) {
        return new StringExpressionImpl<>((Operation) null, Expressions.of(path));
    }

    @Override
    public <U extends Number & Comparable<U>> NumberPathExpression<T, U> number(Path<T, U> path) {
        return new NumberExpressionImpl<>((Operation) null, Expressions.of(path));
    }

    @Override
    public <U extends Comparable<U>> ComparablePathExpression<T, U> comparable(Path<T, U> path) {
        return new ComparableExpressionImpl<>((Operation) null, Expressions.of(path));
    }

    @Override
    public BooleanPathExpression<T> bool(Path<T, Boolean> path) {
        return new BooleanExpressionImpl<>((Operation) null, Expressions.of(path));
    }

}
