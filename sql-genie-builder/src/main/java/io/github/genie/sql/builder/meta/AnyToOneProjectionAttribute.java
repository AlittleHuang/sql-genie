package io.github.genie.sql.builder.meta;

public interface AnyToOneProjectionAttribute extends Attribute, ObjectType {

    Attribute entityAttribute();

}
