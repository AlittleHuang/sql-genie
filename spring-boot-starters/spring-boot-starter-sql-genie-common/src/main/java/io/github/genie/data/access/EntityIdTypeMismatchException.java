package io.github.genie.data.access;

/**
 * @author huang
 * @since 2024-03-16
 */
public class EntityIdTypeMismatchException extends RuntimeException {
    public EntityIdTypeMismatchException(String message) {
        super(message);
    }
}
