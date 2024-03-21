package io.github.genie.sql.data.example.model;

import io.github.genie.sql.api.TypedExpression;

/**
 * @author HuangChengwei
 * @since 2024-03-19 17:15
 */
public interface PageablePredicate<T> {

    Integer getPage();

    Integer getSize();

    TypedExpression<T, Boolean> predicate();

    default Pageable<T> pageable() {
        return new DefaultPageable<>(getPage(), getSize());
    }

}