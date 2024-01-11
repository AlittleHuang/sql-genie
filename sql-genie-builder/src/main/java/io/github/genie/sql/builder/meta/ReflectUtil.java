package io.github.genie.sql.builder.meta;

import lombok.SneakyThrows;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ReflectUtil {
    public static Field getDeclaredField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                return getDeclaredField(superclass, name);
            }
        }
        return null;
    }

    @SneakyThrows
    public static <T> void copyTargetNullFields(T src, T target, Class<T> type) {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            Method reader = descriptor.getReadMethod();
            Method writer = descriptor.getWriteMethod();
            if (reader != null && writer != null) {
                Object tv = reader.invoke(target);
                if (tv != null) {
                    continue;
                }
                Object sv = reader.invoke(src);
                if (sv != null) {
                    writer.invoke(target, sv);
                }
            }
        }
    }

    public static Object invokeDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable {
        return InvocationHandler.invokeDefault(proxy, method, args);
    }

    public static Object getFieldValue(Field field, Object instance) throws IllegalAccessException {
        checkAccessible(field, instance);
        return field.get(instance);
    }

    public static void setFieldValue(Field field, Object instance, Object value) throws IllegalAccessException {
        checkAccessible(field, instance);
        field.set(instance, value);
    }

    private static void checkAccessible(Field field, Object instance) {
        if (!isAccessible(field, instance)) {
            field.setAccessible(true);
        }
    }

    public static boolean isAccessible(AccessibleObject accessibleObject, Object instance) {
        return accessibleObject.canAccess(instance);
    }

}
