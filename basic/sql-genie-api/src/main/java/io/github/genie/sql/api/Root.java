package io.github.genie.sql.api;

import io.github.genie.sql.api.Path.BooleanPath;
import io.github.genie.sql.api.Path.ComparablePath;
import io.github.genie.sql.api.Path.NumberPath;
import io.github.genie.sql.api.Path.StringPath;
import io.github.genie.sql.api.TypedExpression.BooleanPathExpression;
import io.github.genie.sql.api.TypedExpression.ComparablePathExpression;
import io.github.genie.sql.api.TypedExpression.EntityPathExpression;
import io.github.genie.sql.api.TypedExpression.NumberPathExpression;
import io.github.genie.sql.api.TypedExpression.StringPathExpression;

public interface Root<T> {

    <U> TypedExpression<T, U> of(U value);

    <U> EntityPathExpression<T, U> get(Path<T, U> path);

    StringPathExpression<T> get(StringPath<T> path);

    <U extends Number & Comparable<U>> NumberPathExpression<T, U> get(NumberPath<T, U> path);

    <U extends Comparable<U>> ComparablePathExpression<T, U> get(ComparablePath<T, U> path);

    BooleanPathExpression<T> get(BooleanPath<T> path);

    <U> EntityPathExpression<T, U> entity(Path<T, U> path);

    StringPathExpression<T> string(Path<T, String> path);

    <U extends Number & Comparable<U>> NumberPathExpression<T, U> number(Path<T, U> path);

    <U extends Comparable<U>> ComparablePathExpression<T, U> comparable(Path<T, U> path);

    BooleanPathExpression<T> bool(Path<T, Boolean> path);

}
