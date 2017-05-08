package com.mymo.transformer.handler;

import com.mymo.transformer.exception.TransformException;

/**
 * 
 * @author Phil Madden
 *
 */
public interface TransformHandler {
	<T> T transform(T value) throws TransformException;
}
