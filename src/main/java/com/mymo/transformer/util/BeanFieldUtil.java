package com.mymo.transformer.util;

import java.lang.reflect.Field;
import java.math.*;
import java.sql.*;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * @author Phil Madden
 *
 */
public class BeanFieldUtil {
	private static final String BOOLEAN_PREFIX = "is";
	private static final String ACCESSOR_PREFIX = "get";
	private static final String MUTATOR_PREFIX = "set";
	
	public static String getAccessorMethodName(Field field) {
		if (field.getType() == Boolean.class && !isPrimitiveWrapper(field.getType())) {
			return getMethodName(BOOLEAN_PREFIX, field.getName());
		} else {
			return getMethodName(ACCESSOR_PREFIX, field.getName());
		}
	}

	public static String getMutatorMethodName(Field field) {
		return getMethodName(MUTATOR_PREFIX, field.getName());
	}
	
	private static String getMethodName(String prefix, String fieldName) {
	    if (isSecondCharUppercase(fieldName)) {
            return String.format("%s%s", prefix, fieldName);
        } else {
		    return String.format("%s%s", prefix, upperFirstChar(fieldName));
        }

	}

	private static boolean isSecondCharUppercase(String value) {
	    if(value.length() > 1) {
            return Character.isUpperCase(value.charAt(1));
        }
        return false;
    }

	private static String upperFirstChar(String value) {
		if (value.length() > 1) {
			return value.substring(0, 1).toUpperCase() + value.substring(1, value.length());
		}
		return value.toUpperCase();
	}
	
	public static boolean isComplexType(Class<?> clazz) {
		return !clazz.isPrimitive() && !clazz.isArray() && !clazz.isAnnotation()
				&& !clazz.isEnum() && !isPrimitiveWrapper(clazz) && !isString(clazz)
				&& !isDateOrTime(clazz) && !isBigNumber(clazz) && !isCollection(clazz);

	}

	public static <T> boolean isPrimitiveWrapper(Class<T> clazz) {
		return clazz == Double.class || clazz == Float.class || clazz == Long.class ||
				clazz == Integer.class || clazz == Short.class || clazz == Character.class ||
				clazz == Byte.class || clazz == Boolean.class;
	}

	public static <T> boolean isCollection(Class<T> clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}
	
	public static <T> boolean isString(Class<T> clazz) {
		return String.class == clazz;
	}

	public static <T> boolean isBigNumber(Class<T> clazz) {
		return BigDecimal.class == clazz || BigInteger.class == clazz;
	}

	public static <T> boolean isDateOrTime(Class<T> clazz) {
		return Date.class == clazz
				|| java.sql.Date.class == clazz
				|| Timestamp.class == clazz
				|| Time.class == clazz;
	}
}
