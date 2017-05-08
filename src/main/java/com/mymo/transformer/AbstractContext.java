package com.mymo.transformer;

import java.lang.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.*;

import com.mymo.transformer.annotation.Configuration;
import com.mymo.transformer.annotation.Translation;
import com.mymo.transformer.configuration.*;
import com.mymo.transformer.exception.InstantiationException;

/**
 * 
 * @author Phil Madden
 *
 */
public abstract class AbstractContext implements Context {
    private static final Logger LOGGER = Logger.getLogger(AbstractContext.class.getCanonicalName());
	protected static final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
	protected static ObjectFactory objectFactory;
	protected static BeanNamingStrategy beanNamingStrategy;
	protected static BeanDefinitionFactory beanDefinitionFactory;

	private static Class<?> configuration;
	private static Configuration configurationAnnotation;

	public abstract Map<String, BeanDefinition> defineBeans();

	protected AbstractContext(Class<?> configurationClass) {
		if (!configurationClass.isAnnotationPresent(Configuration.class)) {
			throw new RuntimeException("Specified class must be annotated with @MergerConfiguration.");
		}

		init(configurationClass);
		defineExplicitlyDefinedBeans();
	}

	private void init(Class<?> configurationClass) {
		try {
			configuration = configurationClass;
			configurationAnnotation = configuration.getAnnotation(Configuration.class);
			objectFactory = configurationAnnotation.objectFactory().newInstance();
			beanNamingStrategy = newInstance(configurationAnnotation.beanNamingStrategy());
			beanDefinitionFactory = newInstance(configurationAnnotation.beanDefinitionFactory());
		} catch (com.mymo.transformer.exception.InstantiationException | java.lang.InstantiationException | IllegalAccessException e) {
			LOGGER.log(Level.FINE, e.getMessage(), e);
		}
	}

	private void defineExplicitlyDefinedBeans() {
		defineExplicitlyDefinedBeans(getExplicitEntities());
		beanDefinitionMap.putAll(defineBeans());
	}

	private void defineExplicitlyDefinedBeans(Set<Class<?>> entities) {
		for (Class<?> beanClass : entities) {
			if (beanDefinitionMap.containsKey(beanNamingStrategy.getName(beanClass))) {
				continue;
			}
			BeanDefinition beanDefinition = beanDefinitionFactory.getBeanDefinition(beanClass);
			beanDefinitionMap.put(beanNamingStrategy.getName(beanClass), beanDefinition);
			defineExplicitlyDefinedBeans(beanDefinition.getComplexBeanFieldDefinitions());
		}
	}

	private static Set<Class<?>> getExplicitEntities() {
		Set<Class<?>> beanClasses = new HashSet<>();
		Translation translation;
		for (Field field : configuration.getDeclaredFields()) {
			translation = field.getAnnotation(Translation.class);
			if (translation != null) {
				beanClasses.add(field.getType());
				beanClasses.add(translation.value());

			}
		}

		return beanClasses;
	}

	@Override
	public final BeanDefinition getBeanDefinition(Class tClass) {
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanNamingStrategy.getName(tClass));

		if (beanDefinition == null) {
			throw new RuntimeException("No Bean Definition found for type: " + tClass.getCanonicalName());
		}

		return beanDefinition;
	}

	@Override
	public final <T> T newInstance(Class<T> tClass) throws InstantiationException {
		T t = objectFactory.getInstance(tClass);
		if (ContextAware.class.isAssignableFrom(tClass)) {
			((ContextAware) t).setContext(this);
		}
		return t;
	}
}
