package io.github.genie.sql.builder.util;

import io.github.genie.sql.builder.TypeCastUtil;

import java.util.Objects;

/**
 * @author HuangChengwei
 * @since 2024-03-22 9:13
 */
public class Exceptions {

    public static RuntimeException sneakyThrow(Throwable throwable) {
        Objects.requireNonNull(throwable, "throwable");
        return Exceptions.sneakyThrow0(throwable);
    }

    private static <T extends Throwable> T sneakyThrow0(Throwable t) throws T {
        throw TypeCastUtil.<T>unsafeCast(t);
    }

}
