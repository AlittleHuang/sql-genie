package io.github.genie.data.jdbc;

import io.github.genie.data.access.BaseDbAccessConfiguration;
import io.github.genie.data.access.TransactionalUpdate;
import io.github.genie.sql.api.Query;
import io.github.genie.sql.api.Update;
import io.github.genie.sql.builder.AbstractQueryExecutor;
import io.github.genie.sql.builder.QueryStructurePostProcessor;
import io.github.genie.sql.builder.meta.Metamodel;
import io.github.genie.sql.executor.jdbc.ConnectionProvider;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor.QuerySqlBuilder;
import io.github.genie.sql.executor.jdbc.JdbcQueryExecutor.ResultCollector;
import io.github.genie.sql.executor.jdbc.JdbcResultCollector;
import io.github.genie.sql.executor.jdbc.JdbcUpdate;
import io.github.genie.sql.executor.jdbc.JdbcUpdateSqlBuilder;
import io.github.genie.sql.executor.jdbc.MySqlQuerySqlBuilder;
import io.github.genie.sql.executor.jdbc.MysqlUpdateSqlBuilder;
import io.github.genie.sql.meta.JpaMetamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Import(BaseDbAccessConfiguration.class)
public class JdbcAccessConfiguration {

    @Bean
    @ConditionalOnMissingBean
    Metamodel genieMetamodel() {
        return JpaMetamodel.of();
    }

    @Bean
    @ConditionalOnMissingBean
    AbstractQueryExecutor queryExecutor(Metamodel metamodel,
                                        QuerySqlBuilder querySqlBuilder,
                                        ResultCollector resultCollector,
                                        ConnectionProvider connectionProvider) {
        return new JdbcQueryExecutor(metamodel, querySqlBuilder, connectionProvider, resultCollector);
    }

    @Bean
    @ConditionalOnMissingBean
    ConnectionProvider connectionProvider(JdbcTemplate jdbcTemplate) {
        return new ConnectionProvider() {
            @Override
            public <T> T execute(ConnectionCallback<T> action) {
                return jdbcTemplate.execute(action::doInConnection);
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean
    ResultCollector resultCollector() {
        return new JdbcResultCollector();
    }

    @Bean
    @ConditionalOnMissingBean
    QuerySqlBuilder querySqlBuilder() {
        return new MySqlQuerySqlBuilder();
    }

    @Bean
    @ConditionalOnMissingBean
    Query genieQuery(AbstractQueryExecutor executor,
                     @Autowired(required = false) QueryStructurePostProcessor structurePostProcessor) {
        return structurePostProcessor != null
                ? executor.createQuery(structurePostProcessor)
                : executor.createQuery();
    }

    @Bean
    @ConditionalOnMissingBean
    Update genieUpdate(JdbcUpdateSqlBuilder sqlBuilder,
                       ConnectionProvider connectionProvider,
                       Metamodel metamodel) {
        JdbcUpdate jdbcUpdate = new JdbcUpdate(sqlBuilder, connectionProvider, metamodel);
        return new TransactionalUpdate(jdbcUpdate);
    }

    @Bean
    @ConditionalOnMissingBean
    JdbcUpdateSqlBuilder jdbcUpdateSqlBuilder() {
        return new MysqlUpdateSqlBuilder();
    }

}
