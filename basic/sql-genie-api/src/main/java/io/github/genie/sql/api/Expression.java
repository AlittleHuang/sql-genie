package io.github.genie.sql.api;

import java.io.Serializable;

public interface Expression extends TypedExpression<Object, Object>, Serializable {
    @Override
    default Expression expression() {
        return this;
    }

}
