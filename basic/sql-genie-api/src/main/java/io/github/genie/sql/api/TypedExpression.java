package io.github.genie.sql.api;

import io.github.genie.sql.api.ExpressionOperator.AndOperator;
import io.github.genie.sql.api.ExpressionOperator.OrOperator;
import io.github.genie.sql.api.Path.BooleanPath;
import io.github.genie.sql.api.Path.ComparablePath;
import io.github.genie.sql.api.Path.NumberPath;
import io.github.genie.sql.api.Path.StringPath;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unused")
public interface TypedExpression<T, U> {

    Expression expression();

    interface BasicExpression<T, U> extends TypedExpression<T, U> {

        Root<T> root();

        TypedExpression.NumberExpression<T, Long> count();

        TypedExpression.BooleanExpression<T> eq(U value);

        TypedExpression.BooleanExpression<T> eq(TypedExpression<T, U> value);

        TypedExpression.BooleanExpression<T> ne(U value);

        TypedExpression.BooleanExpression<T> ne(TypedExpression<T, U> value);

        @SuppressWarnings("unchecked")
        TypedExpression.BooleanExpression<T> in(U... values);

        TypedExpression.BooleanExpression<T> in(@NotNull List<? extends TypedExpression<T, U>> values);

        TypedExpression.BooleanExpression<T> in(@NotNull Collection<? extends U> values);

        @SuppressWarnings("unchecked")
        TypedExpression.BooleanExpression<T> notIn(U... values);

        TypedExpression.BooleanExpression<T> notIn(@NotNull List<? extends TypedExpression<T, U>> values);

        TypedExpression.BooleanExpression<T> notIn(@NotNull Collection<? extends U> values);

        TypedExpression.BooleanExpression<T> isNull();

        TypedExpression.BooleanExpression<T> isNotNull();

    }

    interface BooleanExpression<T> extends AndOperator<T>, OrOperator<T>, Predicate<T> {
    }

    interface ComparableExpression<T, U extends Comparable<U>> extends BasicExpression<T, U> {
        BooleanExpression<T> ge(TypedExpression<T, U> expression);

        BooleanExpression<T> gt(TypedExpression<T, U> expression);

        BooleanExpression<T> le(TypedExpression<T, U> expression);

        BooleanExpression<T> lt(TypedExpression<T, U> expression);

        BooleanExpression<T> between(TypedExpression<T, U> l, TypedExpression<T, U> r);

        BooleanExpression<T> notBetween(TypedExpression<T, U> l, TypedExpression<T, U> r);

        Order<T> asc();

        Order<T> desc();

        default BooleanExpression<T> ge(U value) {
            return ge(root().of(value));
        }

        default BooleanExpression<T> gt(U value) {
            return gt(root().of(value));
        }

        default BooleanExpression<T> le(U value) {
            return le(root().of(value));
        }

        default BooleanExpression<T> lt(U value) {
            return lt(root().of(value));
        }

        default BooleanExpression<T> between(U l, U r) {
            Root<T> eb = root();
            return between(eb.of(l), eb.of(r));
        }

        default BooleanExpression<T> notBetween(U l, U r) {
            Root<T> eb = root();
            return notBetween(eb.of(l), eb.of(r));
        }

        default BooleanExpression<T> between(TypedExpression<T, U> l, U r) {
            return between(l, root().of(r));
        }

        default BooleanExpression<T> between(U l, TypedExpression<T, U> r) {
            return between(root().of(l), r);
        }

        default BooleanExpression<T> notBetween(TypedExpression<T, U> l, U r) {
            return notBetween(l, root().of(r));
        }

        default BooleanExpression<T> notBetween(U l, TypedExpression<T, U> r) {
            return notBetween(root().of(l), r);
        }

    }

    interface NumberExpression<T, U extends Number & Comparable<U>> extends ComparableExpression<T, U> {
        TypedExpression.NumberExpression<T, U> add(TypedExpression<T, U> expression);

        TypedExpression.NumberExpression<T, U> subtract(TypedExpression<T, U> expression);

        TypedExpression.NumberExpression<T, U> multiply(TypedExpression<T, U> expression);

        TypedExpression.NumberExpression<T, U> divide(TypedExpression<T, U> expression);

        TypedExpression.NumberExpression<T, U> mod(TypedExpression<T, U> expression);

        TypedExpression.NumberExpression<T, U> sum();

        <R extends Number & Comparable<R>> TypedExpression.NumberExpression<T, R> avg();

        TypedExpression.NumberExpression<T, U> max();

        TypedExpression.NumberExpression<T, U> min();

        default TypedExpression.NumberExpression<T, U> add(U value) {
            return add(root().of(value));
        }

        default TypedExpression.NumberExpression<T, U> subtract(U value) {
            return subtract(root().of(value));
        }

        default TypedExpression.NumberExpression<T, U> multiply(U value) {
            return multiply(root().of(value));
        }

        default TypedExpression.NumberExpression<T, U> divide(U value) {
            return divide(root().of(value));
        }

        default TypedExpression.NumberExpression<T, U> mod(U value) {
            return mod(root().of(value));
        }

    }

    interface PathExpression<T, U> extends BasicExpression<T, U> {
        <R> TypedExpression.PathExpression<T, R> get(Path<U, R> path);

        TypedExpression.StringExpression<T> get(StringPath<U> path);

        <R extends Number & Comparable<R>> NumberExpression<T, R> get(NumberPath<U, R> path);

        <R extends Comparable<R>> ComparableExpression<T, R> get(ComparablePath<U, R> path);

        BooleanExpression<T> get(BooleanPath<U> path);

    }

    interface Predicate<T> extends TypedExpression<T, Boolean> {
        Predicate<T> not();

    }

    interface StringExpression<T> extends ComparableExpression<T, String> {
        BooleanExpression<T> like(String value);

        default BooleanExpression<T> startWith(String value) {
            return like(value + '%');
        }

        default BooleanExpression<T> endsWith(String value) {
            return like('%' + value);
        }

        default BooleanExpression<T> contains(String value) {
            return like('%' + value + '%');
        }

        BooleanExpression<T> notLike(String value);

        default BooleanExpression<T> notStartWith(String value) {
            return notLike(value + '%');
        }

        default BooleanExpression<T> notEndsWith(String value) {
            return notLike('%' + value);
        }

        default BooleanExpression<T> notContains(String value) {
            return notLike('%' + value + '%');
        }

        TypedExpression.StringExpression<T> lower();

        TypedExpression.StringExpression<T> upper();

        TypedExpression.StringExpression<T> substring(int a, int b);

        TypedExpression.StringExpression<T> substring(int a);

        TypedExpression.StringExpression<T> trim();

        NumberExpression<T, Integer> length();
    }
}
