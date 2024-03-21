package io.github.genie.sql.builder;

import io.github.genie.sql.api.ExpressionOperator;
import io.github.genie.sql.api.Path;
import io.github.genie.sql.api.Path.ComparablePath;
import io.github.genie.sql.api.Path.NumberPath;
import io.github.genie.sql.api.Path.StringPath;
import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.api.TypedExpression.BasicExpression;
import io.github.genie.sql.api.TypedExpression.ComparableExpression;
import io.github.genie.sql.api.TypedExpression.EntityPathExpression;
import io.github.genie.sql.api.TypedExpression.NumberExpression;
import io.github.genie.sql.api.TypedExpression.StringExpression;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

class DefaultExpressionOperator<T, U, B> implements ExpressionOperator<T, U, B> {

    protected final BasicExpression<T, U> base;
    protected final Function<? super BasicExpression<?, ?>, B> resultBuilder;

    DefaultExpressionOperator(BasicExpression<T, U> base, Function<? super BasicExpression<?, ?>, B> resultBuilder) {
        this.base = base;
        this.resultBuilder = resultBuilder;
    }

    @Override
    public B eq(U value) {
        return resultBuilder.apply(base.eq(value));
    }

    @Override
    public B eqIfNotNull(U value) {
        return resultBuilder.apply(value == null ? null : base.eq(value));
    }

    @Override
    public B eq(TypedExpression<T, U> expression) {
        return resultBuilder.apply(base.eq(expression));
    }

    @Override
    public B ne(U value) {
        return resultBuilder.apply(base.ne(value));
    }

    @Override
    public B neIfNotNull(U value) {
        return resultBuilder.apply(value == null ? null : base.ne(value));
    }

    @Override
    public B ne(TypedExpression<T, U> expression) {
        return resultBuilder.apply(base.ne(expression));
    }

    @Override
    @SafeVarargs
    public final B in(U... values) {
        return resultBuilder.apply(base.in(values));
    }

    @Override
    public B in(@NotNull List<? extends TypedExpression<T, U>> expressions) {
        return resultBuilder.apply(base.in(expressions));
    }

    @Override
    public B in(@NotNull Collection<? extends U> values) {
        return resultBuilder.apply(base.in(values));
    }

    @Override
    @SafeVarargs
    public final B notIn(U... values) {
        return resultBuilder.apply(base.notIn(values));
    }

    @Override
    public B notIn(@NotNull List<? extends TypedExpression<T, U>> expressions) {
        return resultBuilder.apply(base.notIn(expressions));
    }

    @Override
    public B notIn(@NotNull Collection<? extends U> values) {
        return resultBuilder.apply(base.notIn(values));
    }

    @Override
    public B isNull() {
        return resultBuilder.apply(base.isNull());
    }

    @Override
    public B isNotNull() {
        return resultBuilder.apply(base.isNotNull());
    }

    static class ComparableOperatorImpl<T, U extends Comparable<U>, B> extends DefaultExpressionOperator<T, U, B> implements ComparableOperator<T, U, B> {

        public ComparableOperatorImpl(ComparableExpression<T, U> expression, Function<? super BasicExpression<?, ?>, B> resultBuilder) {
            super(expression, resultBuilder);
        }

        protected ComparableExpression<T, U> base() {
            return (ComparableExpression<T, U>) base;
        }

        @Override
        public B ge(U value) {
            return resultBuilder.apply(base().ge(value));
        }

        @Override
        public B gt(U value) {
            return resultBuilder.apply(base().gt(value));
        }

        @Override
        public B le(U value) {
            return resultBuilder.apply(base().le(value));
        }

        @Override
        public B lt(U value) {
            return resultBuilder.apply(base().lt(value));
        }

        @Override
        public B geIfNotNull(U value) {
            return resultBuilder.apply(value == null ? null : base().ge(value));
        }

        @Override
        public B gtIfNotNull(U value) {
            return resultBuilder.apply(value == null ? null : base().gt(value));
        }

        @Override
        public B leIfNotNull(U value) {
            return resultBuilder.apply(value == null ? null : base().le(value));
        }

        @Override
        public B ltIfNotNull(U value) {
            return resultBuilder.apply(value == null ? null : base().lt(value));
        }

        @Override
        public B between(U l, U r) {
            return resultBuilder.apply(base().between(l, r));
        }

        @Override
        public B notBetween(U l, U r) {
            return resultBuilder.apply(base().notBetween(l, r));
        }

        @Override
        public B ge(TypedExpression<T, U> expression) {
            return resultBuilder.apply(base().ge(expression));
        }

        @Override
        public B gt(TypedExpression<T, U> expression) {
            return resultBuilder.apply(base().gt(expression));
        }

        @Override
        public B le(TypedExpression<T, U> expression) {
            return resultBuilder.apply(base().le(expression));
        }

        @Override
        public B lt(TypedExpression<T, U> expression) {
            return resultBuilder.apply(base().lt(expression));
        }

        @Override
        public B between(TypedExpression<T, U> l, TypedExpression<T, U> r) {
            return resultBuilder.apply(base().between(l, r));
        }

        @Override
        public B between(TypedExpression<T, U> l, U r) {
            return resultBuilder.apply(base().between(l, r));
        }

        @Override
        public B between(U l, TypedExpression<T, U> r) {
            return resultBuilder.apply(base().between(l, r));
        }

        @Override
        public B notBetween(TypedExpression<T, U> l, TypedExpression<T, U> r) {
            return resultBuilder.apply(base().notBetween(l, r));
        }

        @Override
        public B notBetween(TypedExpression<T, U> l, U r) {
            return resultBuilder.apply(base().notBetween(l, r));
        }

        @Override
        public B notBetween(U l, TypedExpression<T, U> r) {
            return resultBuilder.apply(base().notBetween(l, r));
        }
    }

    static class NumberOperatorImpl<T, U extends Number & Comparable<U>, B> extends ComparableOperatorImpl<T, U, B> implements NumberOperator<T, U, B> {

        public NumberOperatorImpl(NumberExpression<T, U> expression, Function<? super BasicExpression<?, ?>, B> resultBuilder) {
            super(expression, resultBuilder);
        }

        @Override
        protected NumberExpression<T, U> base() {
            return (NumberExpression<T, U>) base;
        }

        @Override
        public NumberOperator<T, U, B> add(U value) {
            return new NumberOperatorImpl<>(base().add(value), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> subtract(U value) {
            return new NumberOperatorImpl<>(base().subtract(value), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> multiply(U value) {
            return new NumberOperatorImpl<>(base().multiply(value), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> divide(U value) {
            return new NumberOperatorImpl<>(base().divide(value), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> mod(U value) {
            return new NumberOperatorImpl<>(base().mod(value), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> addIfNotNull(U value) {
            return value == null ? this : add(value);
        }

        @Override
        public NumberOperator<T, U, B> subtractIfNotNull(U value) {
            return value == null ? this : subtract(value);
        }

        @Override
        public NumberOperator<T, U, B> multiplyIfNotNull(U value) {
            return value == null ? this : multiply(value);
        }

        @Override
        public NumberOperator<T, U, B> divideIfNotNull(U value) {
            return value == null ? this : divide(value);
        }

        @Override
        public NumberOperator<T, U, B> modIfNotNull(U value) {
            return value == null ? this : mod(value);
        }

        @Override
        public NumberOperator<T, U, B> add(TypedExpression<T, U> expression) {
            return new NumberOperatorImpl<>(base().add(expression), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> subtract(TypedExpression<T, U> expression) {
            return new NumberOperatorImpl<>(base().subtract(expression), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> multiply(TypedExpression<T, U> expression) {
            return new NumberOperatorImpl<>(base().multiply(expression), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> divide(TypedExpression<T, U> expression) {
            return new NumberOperatorImpl<>(base().divide(expression), resultBuilder);
        }

        @Override
        public NumberOperator<T, U, B> mod(TypedExpression<T, U> expression) {
            return new NumberOperatorImpl<>(base().mod(expression), resultBuilder);
        }

    }

    static class PathOperatorImpl<T, U, B> extends DefaultExpressionOperator<T, U, B> implements PathOperator<T, U, B> {

        public PathOperatorImpl(BasicExpression<T, U> expression, Function<? super BasicExpression<?, ?>, B> resultBuilder) {
            super(expression, resultBuilder);
        }

        protected EntityPathExpression<T, U> base() {
            return (EntityPathExpression<T, U>) base;
        }

        @Override
        public <V> PathOperator<T, V, B> get(Path<U, V> path) {
            return new PathOperatorImpl<>(base().get(path), resultBuilder);
        }

        @Override
        public StringOperator<T, B> get(StringPath<U> path) {
            StringExpression<T> tStringExpression = base().get(path);
            return new StringOperatorImpl<>(tStringExpression, resultBuilder);
        }

        @Override
        public <V extends Number & Comparable<V>> NumberOperator<T, V, B> get(NumberPath<U, V> path) {
            return new NumberOperatorImpl<>(base().get(path), resultBuilder);
        }

        @Override
        public <V extends Comparable<V>> ComparableOperator<T, V, B> get(ComparablePath<U, V> path) {
            return new ComparableOperatorImpl<>(base().get(path), resultBuilder);
        }

    }

    static class StringOperatorImpl<T, B> extends ComparableOperatorImpl<T, String, B> implements StringOperator<T, B> {

        public StringOperatorImpl(StringExpression<T> expression, Function<? super BasicExpression<?, ?>, B> resultBuilder) {
            super(expression, resultBuilder);
        }

        @Override
        protected StringExpression<T> base() {
            return (StringExpression<T>) base;
        }

        @Override
        public B like(String value) {
            return resultBuilder.apply(base().like(value));
        }

        @Override
        public B notLike(String value) {
            return resultBuilder.apply(base().notLike(value));
        }

        @Override
        public B likeIfNotNull(String value) {
            return resultBuilder.apply(value == null ? null : base().like(value));
        }

        @Override
        public B notLikeIfNotNull(String value) {
            return resultBuilder.apply(value == null ? null : base().notLike(value));
        }

        @Override
        public StringOperator<T, B> lower() {
            return new StringOperatorImpl<>(base().lower(), resultBuilder);
        }

        @Override
        public StringOperator<T, B> upper() {
            return new StringOperatorImpl<>(base().upper(), resultBuilder);
        }

        @Override
        public StringOperator<T, B> substring(int offset, int length) {
            return new StringOperatorImpl<>(base().substring(offset, length), resultBuilder);
        }

        @Override
        public StringOperator<T, B> substring(int offset) {
            return new StringOperatorImpl<>(base().substring(offset), resultBuilder);
        }

        @Override
        public StringOperator<T, B> trim() {
            return new StringOperatorImpl<>(base().trim(), resultBuilder);
        }

        @Override
        public NumberOperator<T, Integer, B> length() {
            return new NumberOperatorImpl<>(base().length(), resultBuilder);
        }
    }
}
