package io.github.genie.sql.api;

/**
 * @author HuangChengwei
 * @since 2024-03-25 9:50
 */
non-sealed public interface SubQuery extends Expression {

    QueryStructure queryStructure();

}
