package io.github.genie.sql.builder;

import io.github.genie.sql.api.Expression;
import io.github.genie.sql.api.TypedExpression;

import java.util.List;

public class TypeCastUtil {

    public static <T> List<T> cast(List<?> expression) {
        return unsafeCast(expression);
    }

    public static <T> Class<T> cast(Class<?> resolve) {
        return unsafeCast(resolve);
    }

    public static <T, U> TypedExpression<T, U> cast(Expression expression) {
        return unsafeCast(expression);
    }

    public static <T> RootImpl<T> cast(RootImpl<?> builder) {
        return unsafeCast(builder);
    }

    public static <T> T unsafeCast(Object object) {
        // noinspection unchecked
        return (T) object;
    }

}
