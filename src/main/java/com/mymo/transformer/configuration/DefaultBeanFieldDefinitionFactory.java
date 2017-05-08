package com.mymo.transformer.configuration;

import com.mymo.transformer.Context;
import com.mymo.transformer.annotation.TargetField;
import com.mymo.transformer.handler.BasicTransformHandler;
import com.mymo.transformer.handler.TransformHandler;
import com.mymo.transformer.util.*;

import java.lang.reflect.*;
import java.util.logging.*;

public class DefaultBeanFieldDefinitionFactory implements BeanFieldDefinitionFactory, ContextAware {
    private static final Logger LOGGER = Logger.getLogger(DefaultBeanFieldDefinitionFactory.class.getCanonicalName());
    private Context context;

    @Override
    public BeanFieldDefinition getBeanFieldDefinition(Field field) {
        try {
            boolean isComplexType = BeanFieldUtil.isComplexType(field.getType());
            Method accessor = field.getDeclaringClass().getDeclaredMethod(BeanFieldUtil.getAccessorMethodName(field));
            Method mutator = field.getDeclaringClass().getDeclaredMethod(BeanFieldUtil.getMutatorMethodName(field), field.getType());
            return new BeanFieldDefinition(field, accessor, mutator, getTransformHandler(field), isComplexType);

        } catch (NoSuchMethodException e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }

        return null;
    }


    private TransformHandler getTransformHandler(Field field) {
        TransformHandler handler = null;
        TargetField binding = field.getAnnotation(TargetField.class);
        try {
            if (binding != null) {
                handler = context.newInstance(binding.handler());
            } else {
                handler = context.newInstance(BasicTransformHandler.class);
            }
        } catch (com.mymo.transformer.exception.InstantiationException e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }
        return  handler;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
