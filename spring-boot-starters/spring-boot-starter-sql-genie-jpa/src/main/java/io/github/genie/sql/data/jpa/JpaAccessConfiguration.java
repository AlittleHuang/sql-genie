package io.github.genie.sql.data.jpa;

import io.github.genie.sql.data.access.BaseDbAccessConfiguration;
import io.github.genie.sql.data.access.TransactionalUpdate;
import io.github.genie.sql.api.Query;
import io.github.genie.sql.api.Update;
import io.github.genie.sql.builder.AbstractQueryExecutor;
import io.github.genie.sql.builder.QueryStructurePostProcessor;
import io.github.genie.sql.builder.meta.Metamodel;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor.QuerySqlBuilder;
import io.github.genie.sql.executor.jdbc.MySqlQuerySqlBuilder;
import io.github.genie.sql.executor.jpa.JpaQueryExecutor;
import io.github.genie.sql.executor.jpa.JpaUpdate;
import io.github.genie.sql.meta.JpaMetamodel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.SharedEntityManagerCreator;

@Configuration
@Import(BaseDbAccessConfiguration.class)
public class JpaAccessConfiguration {

    @Bean
    @ConditionalOnMissingBean
    protected Metamodel genieMetamodel() {
        return JpaMetamodel.of();
    }

    @Bean
    @ConditionalOnMissingBean
    protected QuerySqlBuilder querySqlBuilder() {
        return new MySqlQuerySqlBuilder();
    }

    @Bean
    @ConditionalOnMissingBean
    protected JpaQueryExecutor queryExecutor(EntityManager entityManager, Metamodel metamodel, QuerySqlBuilder querySqlBuilder) {
        return new JpaQueryExecutor(entityManager, metamodel, querySqlBuilder);
    }

    @Bean
    @ConditionalOnMissingBean
    protected Query genieQuery(AbstractQueryExecutor executor,
                               @Autowired(required = false) QueryStructurePostProcessor structurePostProcessor) {
        return structurePostProcessor != null
                ? executor.createQuery(structurePostProcessor)
                : executor.createQuery();
    }

    @Bean
    @ConditionalOnMissingBean
    protected Update genieUpdate(EntityManager entityManager, JpaQueryExecutor jpaQueryExecutor) {
        JpaUpdate jpaUpdate = new JpaUpdate(entityManager, jpaQueryExecutor);
        return new TransactionalUpdate(jpaUpdate);
    }

    @Bean
    @ConditionalOnMissingBean
    protected EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory);
    }

}
