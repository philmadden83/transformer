package com.mymo.transformer.handler;

import com.mymo.transformer.exception.TransformException;

/**
 * 
 * @author Phil Madden
 *
 */
public class BasicTransformHandler implements TransformHandler {

	@Override
	public <T> T transform(T value) throws TransformException {
		return value;
	}

}
