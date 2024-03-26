package io.github.genie.sql.builder.exception;

public class BeanReflectiveException extends RuntimeException {

    public BeanReflectiveException(String message) {
        super(message);
    }

    public BeanReflectiveException(Throwable cause) {
        super(cause);
    }

}
