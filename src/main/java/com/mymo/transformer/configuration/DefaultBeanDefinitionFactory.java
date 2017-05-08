package com.mymo.transformer.configuration;

import com.mymo.transformer.Context;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultBeanDefinitionFactory implements BeanDefinitionFactory, ContextAware {
    private static final Logger LOGGER = Logger.getLogger(DefaultBeanDefinitionFactory.class.getCanonicalName());
    private BeanFieldDefinitionFactory pojoFieldDefinitionFactory;

    @Override
    public BeanDefinition getBeanDefinition(Class<?> clazz) {
        final BeanDefinition pojoDefinition = new BeanDefinition();

        for (Field field : getFields(clazz)) {
            pojoDefinition.addField(pojoFieldDefinitionFactory.getBeanFieldDefinition(field));
        }

        return pojoDefinition;
    }

    private static Set<Field> getFields(Class<?> pojoClass) {
        final Set<Field> fields = new HashSet<>();
        for (Field field : pojoClass.getDeclaredFields()) {
            fields.add(field);
        }

        return  fields;
    }

    @Override
    public void setContext(Context context) {
        try {
            this.pojoFieldDefinitionFactory = context.newInstance(DefaultBeanFieldDefinitionFactory.class);
        } catch (com.mymo.transformer.exception.InstantiationException e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }
    }
}


