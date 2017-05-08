package com.mymo.transformer;

import com.mymo.transformer.configuration.*;
import com.mymo.transformer.exception.InstantiationException;

public interface Context {
    <T> BeanDefinition getBeanDefinition(Class<T> tClass);
    <T> T newInstance(Class<T> tClass) throws InstantiationException;
}
