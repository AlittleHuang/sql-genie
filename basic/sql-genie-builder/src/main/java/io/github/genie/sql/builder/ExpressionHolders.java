package io.github.genie.sql.builder;

import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.TypedExpression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface ExpressionHolders {

    static <T, U> TypedExpression<T, U> of(Expression expression) {
        return TypeCastUtil.cast(expression);
    }

    static <T, U> TypedExpression<T, U> of(U value) {
        return of(Expressions.of(value));
    }

    static <T, U> List<TypedExpression<T, U>> of(U[] values) {
        return Arrays.stream(values)
                .map(ExpressionHolders::<T, U>of)
                .collect(Collectors.toList());
    }

    static <T, U> List<TypedExpression<T, U>> of(Iterable<? extends U> value) {
        return StreamSupport.stream(value.spliterator(), false)
                .map(ExpressionHolders::<T, U>of)
                .collect(Collectors.toList());
    }

    static <T, U> TypedExpression<T, U> ofTrue() {
        return of(Expressions.TRUE);
    }

}
