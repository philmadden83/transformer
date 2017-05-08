package com.mymo.transformer.annotation;


import com.mymo.transformer.handler.BasicTransformHandler;
import com.mymo.transformer.handler.TransformHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Phil Madden
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetField {
    String value();
    Class<? extends TransformHandler> handler() default BasicTransformHandler.class;
}
