package io.github.genie.sql.builder.converter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author HuangChengwei
 * @since 2024-03-25 13:39
 */
public class LocalDateTimeConverter implements TypeConverter {

    private static final LocalDateTimeConverter INSTANCE = new LocalDateTimeConverter();

    protected LocalDateTimeConverter() {
    }

    public static LocalDateTimeConverter of() {
        return INSTANCE;
    }

    @Override
    public Object convert(Object value, Class<?> targetType) {
        if (value instanceof Date && targetType == LocalDate.class) {
            return ((Date) value).toLocalDate();
        }
        if (value instanceof Timestamp && targetType == LocalDateTime.class) {
            return ((Timestamp) value).toLocalDateTime();
        }
        if (value instanceof Time && targetType == LocalTime.class) {
            return ((Time) value).toLocalTime();
        }
        return value;
    }
}
