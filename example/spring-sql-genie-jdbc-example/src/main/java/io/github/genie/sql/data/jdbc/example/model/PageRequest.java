package io.github.genie.sql.data.jdbc.example.model;

import io.github.genie.sql.api.ExpressionHolder;
import io.github.genie.sql.api.Root;
import io.github.genie.sql.api.Sliceable;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author HuangChengwei
 * @since 2024-03-19 17:15
 */
public interface PageRequest<T> extends Sliceable<T, Page<T>>, Function<Root<T>, ExpressionHolder<T, Boolean>> {
    int DEFAULT_PAGE = 1;
    int DEFAULT_SIZE = 10;

    Integer getPage();

    Integer getSize();

    @Override
    default int offset() {
        return (page() - 1) * limit();
    }

    default int page() {
        return Objects.requireNonNullElse(getPage(), DEFAULT_PAGE);
    }

    @Override
    default int limit() {
        return Objects.requireNonNullElse(getSize(), DEFAULT_SIZE);
    }

    @Override
    default Page<T> collect(List<T> list, long total) {
        PageRequest<T> tPageRequest = this;
        return new Page<>(tPageRequest, list, total);
    }
}
