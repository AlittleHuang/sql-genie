package io.github.genie.sql.builder;

import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.Order;
import io.github.genie.sql.api.Order.SortOrder;
import io.github.genie.sql.api.Path;
import io.github.genie.sql.api.Path.BooleanPath;
import io.github.genie.sql.api.Path.ComparablePath;
import io.github.genie.sql.api.Path.NumberPath;
import io.github.genie.sql.api.Path.StringPath;
import io.github.genie.sql.api.Root;
import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.api.TypedExpression.BooleanExpression;
import io.github.genie.sql.api.TypedExpression.BooleanPathExpression;
import io.github.genie.sql.api.TypedExpression.ComparablePathExpression;
import io.github.genie.sql.api.TypedExpression.NumberExpression;
import io.github.genie.sql.api.TypedExpression.NumberPathExpression;
import io.github.genie.sql.api.TypedExpression.PathExpression;
import io.github.genie.sql.api.TypedExpression.StringPathExpression;
import io.github.genie.sql.builder.QueryStructures.OrderImpl;
import io.github.genie.sql.builder.util.Paths;
import io.github.genie.sql.builder.util.Predicates;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.genie.sql.api.Operator.AND;
import static io.github.genie.sql.api.Operator.NOT;
import static io.github.genie.sql.api.Operator.OR;
import static io.github.genie.sql.api.Order.SortOrder.ASC;
import static io.github.genie.sql.api.Order.SortOrder.DESC;

/**
 * @deprecated use {@link Paths} and {@link Predicates}
 */
@Deprecated
public final class Q {

    public static <T> Root<T> of() {
        return RootImpl.of();
    }

    public static <T, U> PathExpression<T, U> join(Path<T, U> path) {
        return Q.<T>of().get(path);
    }

    public static <T> StringPathExpression<T> get(StringPath<T> path) {
        return Q.<T>of().get(path);
    }

    public static <T, U extends Number & Comparable<U>>
    NumberPathExpression<T, U> get(NumberPath<T, U> path) {
        return Q.<T>of().get(path);
    }

    public static <T, V extends Comparable<V>> ComparablePathExpression<T, V> get(ComparablePath<T, V> path) {
        return Q.<T>of().get(path);
    }

    public static <T> BooleanPathExpression<T> get(BooleanPath<T> path) {
        return Q.<T>of().get(path);
    }

    public static <T, E extends Number & Comparable<E>> NumberExpression<T, E> min(NumberPath<T, E> path) {
        return Q.<T>of().get(path).min();
    }

    public static <T, E extends Number & Comparable<E>> NumberExpression<T, E> max(NumberPath<T, E> path) {
        return Q.<T>of().get(path).max();
    }

    public static <T, E extends Number & Comparable<E>> NumberExpression<T, E> sum(NumberPath<T, E> path) {
        return Q.<T>of().get(path).sum();
    }

    public static <T> NumberExpression<T, Double> avg(NumberPath<T, ?> path) {
        return Q.<T>of().get(path).avg();
    }

    public static <T> NumberExpression<T, Long> count(Path<T, ?> path) {
        return Q.<T>of().get(path).count();
    }

    @SafeVarargs
    public static <T> BooleanExpression<T> and(TypedExpression<T, Boolean> predicate,
                                               TypedExpression<T, Boolean>... predicates) {
        List<Expression> metas = Arrays.stream(predicates)
                .map(TypedExpression::expression)
                .collect(Collectors.toList());
        Expression expression = Expressions.operate(predicate.expression(), AND, metas);
        return TypedExpressions.ofBoolean(expression);
    }

    @SafeVarargs
    public static <T> BooleanExpression<T> or(TypedExpression<T, Boolean> predicate,
                                              TypedExpression<T, Boolean>... predicates) {
        List<Expression> metas = Arrays.stream(predicates)
                .map(TypedExpression::expression)
                .collect(Collectors.toList());
        Expression expression = Expressions.operate(predicate.expression(), OR, metas);
        return TypedExpressions.ofBoolean(expression);
    }

    public static <T> Order<T> desc(Path<T, ? extends Comparable<?>> path) {
        return orderBy(path, DESC);
    }

    public static <T> Order<T> asc(Path<T, ? extends Comparable<?>> path) {
        return orderBy(path, ASC);
    }

    @NotNull
    public static <T> Order<T> orderBy(Path<T, ? extends Comparable<?>> path, SortOrder sortOrder) {
        return new OrderImpl<>(Expressions.of(path), sortOrder);
    }

    @SafeVarargs
    public static <T> List<Order<T>> asc(Path<T, Comparable<?>>... paths) {
        return Arrays.stream(paths)
                .map(Q::asc)
                .collect(Collectors.toList());
    }

    public static <T> BooleanExpression<T> not(TypedExpression<T, Boolean> lt) {
        Expression expression = Expressions.operate(lt.expression(), NOT);
        return TypedExpressions.ofBoolean(expression);
    }

    private Q() {
    }
}
