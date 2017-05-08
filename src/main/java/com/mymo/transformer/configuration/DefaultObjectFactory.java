package com.mymo.transformer.configuration;

import com.mymo.transformer.exception.InstantiationException;
import com.mymo.transformer.util.*;

/**
 * @author Phil Madden
 */
public class DefaultObjectFactory implements ObjectFactory {

    @Override
    public <T> T getInstance(Class<T> tClass) throws InstantiationException {
        try {
            if (BeanFieldUtil.isPrimitiveWrapper(tClass)) {
                return getPrimitiveInstance(tClass);
            } else {
                return tClass.newInstance();
            }
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            throw new InstantiationException(e.getMessage(), e);
        }
    }

    private <T> T getPrimitiveInstance(Class<T> tClass) {
        if (tClass == Boolean.class) {
            return (T) Boolean.TYPE;
        } else if (tClass == Byte.class) {
            return (T) Byte.TYPE;
        } else if (tClass == Character.class) {
            return (T) Character.TYPE;
        } else if (tClass == Double.class) {
            return (T) Double.TYPE;
        } else if (tClass == Float.class) {
            return (T) Float.TYPE;
        } else if (tClass == Integer.class) {
            return (T) Integer.TYPE;
        } else if (tClass == Long.class) {
            return (T) Long.TYPE;
        } else {
            return (T) Short.TYPE;
        }
    }

}
