package io.github.genie.sql.data.access;

import io.github.genie.sql.api.Path;
import io.github.genie.sql.api.Path.BooleanPath;
import io.github.genie.sql.api.Path.ComparablePath;
import io.github.genie.sql.api.Path.NumberPath;
import io.github.genie.sql.api.Path.StringPath;
import io.github.genie.sql.api.Query.Select;
import io.github.genie.sql.api.TypedExpression.BooleanPathExpression;
import io.github.genie.sql.api.TypedExpression.ComparablePathExpression;
import io.github.genie.sql.api.TypedExpression.NumberPathExpression;
import io.github.genie.sql.api.TypedExpression.PathExpression;
import io.github.genie.sql.api.TypedExpression.StringPathExpression;
import io.github.genie.sql.api.Updater;
import io.github.genie.sql.builder.RootImpl;

public interface BaseAccess<T> extends Select<T>, Updater<T> {

    static <T, U> PathExpression<T, U> get(Path<T, U> path) {
        return RootImpl.<T>of().get(path);
    }

    static <T> StringPathExpression<T> get(StringPath<T> path) {
        return RootImpl.<T>of().get(path);
    }

    static <T, U extends Number & Comparable<U>> NumberPathExpression<T, U> get(NumberPath<T, U> path) {
        return RootImpl.<T>of().get(path);
    }

    static <T, U extends Comparable<U>> ComparablePathExpression<T, U> get(ComparablePath<T, U> path) {
        return RootImpl.<T>of().get(path);
    }

    static <T> BooleanPathExpression<T> get(BooleanPath<T> path) {
        return RootImpl.<T>of().get(path);
    }

}
