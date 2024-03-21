package io.github.genie.sql.builder.util;

import io.github.genie.sql.api.Path;
import io.github.genie.sql.api.Path.BooleanPath;
import io.github.genie.sql.api.Path.ComparablePath;
import io.github.genie.sql.api.Path.NumberPath;
import io.github.genie.sql.api.Path.StringPath;
import io.github.genie.sql.api.TypedExpression.BooleanPathExpression;
import io.github.genie.sql.api.TypedExpression.ComparablePathExpression;
import io.github.genie.sql.api.TypedExpression.JoinPathExpression;
import io.github.genie.sql.api.TypedExpression.NumberPathExpression;
import io.github.genie.sql.api.TypedExpression.StringPathExpression;
import io.github.genie.sql.builder.RootImpl;

public interface Paths {

    static <T, U> JoinPathExpression<T, U> get(Path<T, U> path) {
        return RootImpl.<T>of().join(path);
    }

    static <T> StringPathExpression<T> get(StringPath<T> path) {
        return RootImpl.<T>of().get(path);
    }

    static <T, U extends Number & Comparable<U>> NumberPathExpression<T, U> get(NumberPath<T, U> path) {
        return RootImpl.<T>of().get(path);
    }

    static <T, V extends Comparable<V>> ComparablePathExpression<T, V> get(ComparablePath<T, V> path) {
        return RootImpl.<T>of().get(path);
    }

    static <T> BooleanPathExpression<T> get(BooleanPath<T> path) {
        return RootImpl.<T>of().get(path);
    }

    static <T> StringPathExpression<T> string(Path<T, String> path) {
        return RootImpl.<T>of().string(path);
    }

    static <T, U extends Number & Comparable<U>> NumberPathExpression<T, U> number(Path<T, U> path) {
        return RootImpl.<T>of().number(path);
    }

    static <T, U extends Comparable<U>> ComparablePathExpression<T, U> comparable(Path<T, U> path) {
        return RootImpl.<T>of().comparable(path);
    }

    static <T> BooleanPathExpression<T> bool(Path<T, Boolean> path) {
        return RootImpl.<T>of().bool(path);
    }

}
