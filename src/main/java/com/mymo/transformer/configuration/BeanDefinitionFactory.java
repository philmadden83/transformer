package com.mymo.transformer.configuration;

/**
 * 
 * @author Phil Madden
 *
 */
public interface BeanDefinitionFactory {

	BeanDefinition getBeanDefinition(Class<?> entityClass);
	
}
