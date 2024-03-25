package io.github.genie.sql.builder.converter;

import io.github.genie.sql.api.Lists;

/**
 * @author HuangChengwei
 * @since 2024-03-25 11:37
 */
public interface TypeConverter {

    Object convert(Object value, Class<?> targetType);

    static TypeConverter ofDefault() {
        return of(NumberConverter.of(), EnumConverter.of(), LocalDateTimeConverter.of());
    }

    static TypeConverter of(TypeConverter... converters) {
        return new TypeConverters(Lists.of(converters));
    }

}
