package com.mymo.transformer.configuration;

import java.util.Map;

/**
 * 
 * @author Phil Madden
 *
 */
public interface BeanDefinitionRoutine {

	Map<String, BeanDefinition> run(Class<?> configuration);
	void setEntityDefinitionFactory(Class<? extends BeanDefinitionFactory> entityDefinitionFactory);
	void setEntityNamingStrategy(Class<? extends BeanNamingStrategy> entityNamingStrategy);
}
