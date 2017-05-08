package com.mymo.transformer.configuration;

import java.lang.reflect.*;

public interface BeanFieldDefinitionFactory {

    BeanFieldDefinition getBeanFieldDefinition(Field field);

}
