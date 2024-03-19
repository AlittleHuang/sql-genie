package io.github.genie.data.access;

public class TypeCastUtil {
    public static <T> Class<T> cast(Class<?> resolve) {
        return unsafeCast(resolve);
    }

    public static <T> T unsafeCast(Object o) {
        //noinspection unchecked
        return (T) o;
    }

}
