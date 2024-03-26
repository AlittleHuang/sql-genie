package io.github.genie.sql.data.jpa;

import io.github.genie.sql.api.Update;
import io.github.genie.sql.builder.converter.TypeConverter;
import io.github.genie.sql.builder.meta.Metamodel;
import io.github.genie.sql.data.access.BaseDbAccessConfiguration;
import io.github.genie.sql.data.access.TransactionalUpdate;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor.QuerySqlBuilder;
import io.github.genie.sql.executor.jdbc.MySqlQuerySqlBuilder;
import io.github.genie.sql.executor.jpa.JpaQueryExecutor;
import io.github.genie.sql.executor.jpa.JpaUpdate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.orm.jpa.SharedEntityManagerCreator;

import java.util.List;

@Configuration
@Import(BaseDbAccessConfiguration.class)
public class JpaAccessConfiguration {

    @Bean
    @ConditionalOnMissingBean
    protected QuerySqlBuilder querySqlBuilder() {
        return new MySqlQuerySqlBuilder();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    protected JpaQueryExecutor queryExecutor(EntityManager entityManager,
                                             Metamodel metamodel,
                                             QuerySqlBuilder querySqlBuilder,
                                             List<TypeConverter> typeConverters) {
        TypeConverter converter = TypeConverter.of(typeConverters.toArray(new TypeConverter[0]));
        return new JpaQueryExecutor(entityManager, metamodel, querySqlBuilder, converter);
    }

    @Bean
    @Primary
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

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE >> 2)
    protected TypeConverter typeConverter() {
        return TypeConverter.ofDefault();
    }

}
