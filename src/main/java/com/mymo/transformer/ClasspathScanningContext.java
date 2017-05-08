package com.mymo.transformer;

import java.util.Collections;
import java.util.Map;

import com.mymo.transformer.configuration.BeanDefinition;

/**
 * 
 * @author Phil Madden
 *
 */
public class ClasspathScanningContext extends AbstractContext {

	public ClasspathScanningContext(Class<?> configurationClass) {
		super(configurationClass);
	}

	@Override
	public Map<String, BeanDefinition> defineBeans() {
		return Collections.EMPTY_MAP;
	}

}
