package com.mymo.transformer.configuration;

import com.mymo.transformer.exception.InstantiationException;

public interface ObjectFactory {

    <T> T getInstance(Class<T> tClass) throws InstantiationException;

}
