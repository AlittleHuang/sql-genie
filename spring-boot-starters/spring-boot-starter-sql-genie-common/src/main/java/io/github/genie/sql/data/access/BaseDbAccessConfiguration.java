package io.github.genie.sql.data.access;

import io.github.genie.sql.api.Query;
import io.github.genie.sql.builder.AbstractQueryExecutor;
import io.github.genie.sql.builder.QueryStructurePostProcessor;
import io.github.genie.sql.builder.meta.Metamodel;
import io.github.genie.sql.meta.JpaMetamodel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;

@Slf4j
@Configuration
public class BaseDbAccessConfiguration {

    @Bean
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    protected <T, ID extends Serializable> Access<T, ID> dbAccess(DependencyDescriptor descriptor,
                                                                  Metamodel metamodel) {
        Class<T> entityType = AccessTypeUtil.getEntityType(descriptor);
        Class<?> dependencyType = descriptor.getDependencyType();
        if (Access.class.isAssignableFrom(dependencyType)) {
            checkIdType(descriptor, metamodel, entityType);
        }
        return BaseAccessImpl.access(entityType);
    }

    @Bean
    @ConditionalOnMissingBean
    Query genieQuery(AbstractQueryExecutor executor,
                     @Autowired(required = false)
                     QueryStructurePostProcessor structurePostProcessor) {
        return structurePostProcessor != null
                ? executor.createQuery(structurePostProcessor)
                : executor.createQuery();
    }

    @Bean
    @ConditionalOnMissingBean
    protected Metamodel genieMetamodel() {
        return JpaMetamodel.of();
    }

    private <T, ID extends Serializable> void checkIdType(DependencyDescriptor descriptor,
                                                          Metamodel metamodel,
                                                          Class<T> entityType) {
        Class<ID> idType = AccessTypeUtil.getIdType(descriptor);
        Class<?> expected = metamodel.getEntity(entityType).id().javaType();
        if (expected != idType) {
            String msg = descriptor.getResolvableType() + " " + descriptor
                         + " id type mismatch, expected: " + expected + ", actual: " + idType;
            throw new EntityIdTypeMismatchException(msg);
        }
    }

}
