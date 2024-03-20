package io.github.genie.sql.api;

import java.util.List;

public interface ExpressionBuilder<T> {
    List<? extends TypedExpression<T, ?>> apply(Root<T> root);
}
