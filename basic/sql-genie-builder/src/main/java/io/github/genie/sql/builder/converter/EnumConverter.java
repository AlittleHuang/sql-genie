package io.github.genie.sql.builder.converter;

import io.github.genie.sql.builder.reflect.ReflectUtil;

/**
 * @author HuangChengwei
 * @since 2024-03-25 11:57
 */
public class EnumConverter implements TypeConverter {

    private static final EnumConverter INSTANCE = new EnumConverter();

    public static EnumConverter of() {
        return INSTANCE;
    }

    @Override
    public Object convert(Object value, Class<?> targetType) {
        if (value instanceof Number && targetType.isEnum()) {
            Object v = NumberConverter.of().convert(value, Integer.class);
            if (v instanceof Integer) {
                return ReflectUtil.getEnum(targetType, (Integer) v);
            }
        }
        return value;
    }

    protected EnumConverter() {
    }
}
