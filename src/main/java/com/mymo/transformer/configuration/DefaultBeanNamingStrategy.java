package com.mymo.transformer.configuration;

import java.beans.Introspector;

public class DefaultBeanNamingStrategy implements BeanNamingStrategy {

    @Override
    public String getName(Class<?> pojoClass) {
        return Introspector.decapitalize(pojoClass.getSimpleName());
    }

}
