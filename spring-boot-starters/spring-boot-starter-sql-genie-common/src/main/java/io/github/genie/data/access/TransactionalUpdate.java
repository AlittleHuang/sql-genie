package io.github.genie.data.access;

import io.github.genie.sql.api.Update;
import io.github.genie.sql.api.Updater;
import io.github.genie.sql.builder.UpdaterImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TransactionalUpdate implements Update {

    private final Update target;

    public TransactionalUpdate(Update target) {
        this.target = target;
    }

    @Override
    @Transactional
    public <T> T insert(T entity, Class<T> entityType) {
        return target.insert(entity, entityType);
    }

    @Override
    @Transactional
    public <T> List<T> insert(List<T> entities, Class<T> entityType) {
        return target.insert(entities, entityType);
    }

    @Override
    @Transactional
    public <T> List<T> update(List<T> entities, Class<T> entityType) {
        return target.update(entities, entityType);
    }

    @Override
    @Transactional
    public <T> T update(T entity, Class<T> entityType) {
        return target.update(entity, entityType);
    }

    @Override
    @Transactional
    public <T> void delete(Iterable<T> entities, Class<T> entityType) {
        target.delete(entities, entityType);
    }

    @Override
    @Transactional
    public <T> void delete(T entity, Class<T> entityType) {
        target.delete(entity, entityType);
    }

    @Override
    @Transactional
    public <T> T updateNonNullColumn(T entity, Class<T> entityType) {
        return target.updateNonNullColumn(entity, entityType);
    }

    @Override
    public <T> Updater<T> getUpdater(Class<T> type) {
        return new UpdaterImpl<>(this, type);
    }

}
