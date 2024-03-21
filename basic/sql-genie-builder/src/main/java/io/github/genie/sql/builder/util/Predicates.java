package io.github.genie.sql.builder.util;

import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.TypedExpression;
import io.github.genie.sql.api.TypedExpression.BooleanExpression;
import io.github.genie.sql.builder.Expressions;
import io.github.genie.sql.builder.TypedExpressions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.genie.sql.api.Operator.AND;
import static io.github.genie.sql.api.Operator.NOT;
import static io.github.genie.sql.api.Operator.OR;

/**
 * @author HuangChengwei
 * @since 2024-03-21 14:13
 */
public interface Predicates {

    @SafeVarargs
    static <T> BooleanExpression<T> and(TypedExpression<T, Boolean> predicate,
                                        TypedExpression<T, Boolean>... predicates) {
        List<Expression> metas = Arrays.stream(predicates)
                .map(TypedExpression::expression)
                .collect(Collectors.toList());
        Expression expression = Expressions.operate(predicate.expression(), AND, metas);
        return TypedExpressions.ofBoolean(expression);
    }

    @SafeVarargs
    static <T> BooleanExpression<T> or(TypedExpression<T, Boolean> predicate,
                                       TypedExpression<T, Boolean>... predicates) {
        List<Expression> metas = Arrays.stream(predicates)
                .map(TypedExpression::expression)
                .collect(Collectors.toList());
        Expression expression = Expressions.operate(predicate.expression(), OR, metas);
        return TypedExpressions.ofBoolean(expression);
    }

    static <T> BooleanExpression<T> not(TypedExpression<T, Boolean> lt) {
        Expression expression = Expressions.operate(lt.expression(), NOT);
        return TypedExpressions.ofBoolean(expression);
    }

}
