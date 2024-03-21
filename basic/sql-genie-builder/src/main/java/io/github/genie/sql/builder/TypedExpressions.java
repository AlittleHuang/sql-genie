package io.github.genie.sql.builder;

import io.github.genie.sql.api.Column;
import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.Operation;
import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.api.TypedExpression.BasicExpression;
import io.github.genie.sql.api.TypedExpression.BooleanExpression;
import io.github.genie.sql.api.TypedExpression.BooleanPathExpression;
import io.github.genie.sql.api.TypedExpression.ComparableExpression;
import io.github.genie.sql.api.TypedExpression.ComparablePathExpression;
import io.github.genie.sql.api.TypedExpression.NumberExpression;
import io.github.genie.sql.api.TypedExpression.NumberPathExpression;
import io.github.genie.sql.api.TypedExpression.PathExpression;
import io.github.genie.sql.api.TypedExpression.StringExpression;
import io.github.genie.sql.api.TypedExpression.StringPathExpression;
import io.github.genie.sql.builder.TypedExpressionImpl.BooleanExpressionImpl;
import io.github.genie.sql.builder.TypedExpressionImpl.ComparableExpressionImpl;
import io.github.genie.sql.builder.TypedExpressionImpl.NumberExpressionImpl;
import io.github.genie.sql.builder.TypedExpressionImpl.StringExpressionImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface TypedExpressions {

    static <T, U> TypedExpression<T, U> of(Expression expression) {
        return TypeCastUtil.cast(expression);
    }

    static <T, U> TypedExpression<T, U> of(U value) {
        return of(Expressions.of(value));
    }

    static <T, U> List<TypedExpression<T, U>> of(U[] values) {
        return Arrays.stream(values)
                .map(TypedExpressions::<T, U>of)
                .collect(Collectors.toList());
    }

    static <T, U> List<TypedExpression<T, U>> of(Iterable<? extends U> value) {
        return StreamSupport.stream(value.spliterator(), false)
                .map(TypedExpressions::<T, U>of)
                .collect(Collectors.toList());
    }

    static <T, U> TypedExpression<T, U> ofTrue() {
        return of(Expressions.TRUE);
    }

    static <T, R> PathExpression<T, R> ofPath(Column column) {
        return new TypedExpressionImpl<>((Operation) null, column);
    }

    static <T> StringPathExpression<T> ofString(Column column) {
        return new StringExpressionImpl<>((Operation) null, column);
    }

    static <T> BooleanPathExpression<T> ofBoolean(Column column) {
        return new BooleanExpressionImpl<>((Operation) null, column);
    }

    static <T, U extends Number & Comparable<U>> NumberPathExpression<T, U> ofNumber(Column column) {
        return new NumberExpressionImpl<>((Operation) null, column);
    }

    static <T, U extends Comparable<U>> ComparablePathExpression<T, U> ofComparable(Column column) {
        return new ComparableExpressionImpl<>((Operation) null, column);
    }

    static <T, R> BasicExpression<T, R> ofPath(Expression column) {
        return new TypedExpressionImpl<>((Operation) null, column);
    }

    static <T> StringExpression<T> ofString(Expression column) {
        return new StringExpressionImpl<>((Operation) null, column);
    }

    static <T> BooleanExpression<T> ofBoolean(Expression column) {
        return new BooleanExpressionImpl<>((Operation) null, column);
    }

    static <T, U extends Number & Comparable<U>> NumberExpression<T, U> ofNumber(Expression column) {
        return new NumberExpressionImpl<>((Operation) null, column);
    }

    static <T, U extends Comparable<U>> ComparableExpression<T, U> ofComparable(Expression column) {
        return new ComparableExpressionImpl<>((Operation) null, column);
    }

}
