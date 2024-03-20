package io.github.genie.sql.data.example.model;

import io.github.genie.sql.api.Query.PredicateBuilder;

/**
 * @author HuangChengwei
 * @since 2024-03-19 17:15
 */
public interface PageablePredicate<T> {

    Integer getPage();

    Integer getSize();

    PredicateBuilder<T> predicate();

    default Pageable<T> pageable() {
        return new DefaultPageable<>(getPage(), getSize());
    }

}
