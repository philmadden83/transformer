package com.mymo.transformer.configuration;

import com.mymo.transformer.annotation.TargetField;
import com.mymo.transformer.exception.*;
import com.mymo.transformer.handler.*;

import java.lang.reflect.*;

public class BeanFieldDefinition {
    private final Field field;
    private final String fieldName;
    private final String targetFieldName;
    private final Method accessor;
    private final Method mutator;
    private final boolean complex;
    private final TransformHandler transformHandler;

    public BeanFieldDefinition(Field field, Method accessor, Method mutator, TransformHandler transformHandler, boolean complex) {
        this.field = field;
        this.accessor = accessor;
        this.mutator = mutator;
        this.transformHandler = transformHandler;
        this.complex = complex;
        this.fieldName = field.getName();

        TargetField binding = field.getAnnotation(TargetField.class);

        if (binding != null) {
            targetFieldName = binding.value();
        } else {
            targetFieldName = fieldName;
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getTargetFieldName() {
        return targetFieldName;
    }

    public TransformHandler getTransformHandler() {
        return transformHandler;
    }

    public <T> T getValue(T entity) throws TransformException {
        try {
            return (T) accessor.invoke(entity);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    public <T> void setValue(T entity, Object... values) throws TransformException {
        try {
            mutator.invoke(entity, values);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    public Class getType() {
        return field.getType();
    }

    public boolean isComplex() {
        return complex;
    }

}