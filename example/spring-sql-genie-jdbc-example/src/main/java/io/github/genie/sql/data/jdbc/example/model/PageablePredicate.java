package io.github.genie.sql.data.jdbc.example.model;

import io.github.genie.sql.api.ExpressionHolder;
import io.github.genie.sql.api.Root;
import io.github.genie.sql.data.jdbc.example.eneity.User;

import java.util.function.Function;

/**
 * @author HuangChengwei
 * @since 2024-03-19 17:15
 */
public interface PageablePredicate<T> {

    Integer getPage();

    Integer getSize();

    Function<Root<User>, ExpressionHolder<User, Boolean>> predicate();

    default Pageable<T> pageable() {
        return new DefaultPageable<>(getPage(), getSize());
    }

}
