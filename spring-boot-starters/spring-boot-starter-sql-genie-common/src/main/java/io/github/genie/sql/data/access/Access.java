package io.github.genie.sql.data.access;

public interface Access<T, ID> extends ReadableAccess<T, ID>, WriteableAccess<T> {
}
