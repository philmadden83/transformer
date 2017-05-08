package com.mymo.transformer.configuration;

import java.util.*;

/**
 * 
 * @author Phil Madden
 *
 */
public class BeanDefinition {
	private final Map<String, BeanFieldDefinition> beanFieldDefinitions = new HashMap<>();

	public void addField(BeanFieldDefinition pojoFieldDefinition) {
        beanFieldDefinitions.put(pojoFieldDefinition.getFieldName(), pojoFieldDefinition);
	}
	
	public Collection<BeanFieldDefinition> getBeanFieldDefinitions() {
		return beanFieldDefinitions.values();
	}

	public BeanFieldDefinition getBeanFieldDefinition(String fieldName) {
	    return beanFieldDefinitions.get(fieldName);
    }

	public Set<Class<?>> getComplexBeanFieldDefinitions() {
	    Set<Class<?>> complexPojoFieldDefinitions = new HashSet<>();
        for(BeanFieldDefinition pojoFieldDefinition : getBeanFieldDefinitions()) {
            if (pojoFieldDefinition.isComplex()) {
                complexPojoFieldDefinitions.add(pojoFieldDefinition.getType());
            }
        }

        return complexPojoFieldDefinitions;
    }

}
