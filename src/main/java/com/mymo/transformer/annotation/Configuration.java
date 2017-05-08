package com.mymo.transformer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mymo.transformer.configuration.*;

/**
 * 
 * @author Phil Madden
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
	Class<? extends ObjectFactory> objectFactory() default DefaultObjectFactory.class;
	Class<? extends BeanNamingStrategy> beanNamingStrategy() default DefaultBeanNamingStrategy.class;
	Class<? extends BeanDefinitionFactory> beanDefinitionFactory() default DefaultBeanDefinitionFactory.class;
}
