package io.github.genie.sql.builder.meta;

public interface Metamodel {
    EntityType getEntity(Class<?> type);

    Projection getProjection(Class<?> baseType, Class<?> projectionType);

}
