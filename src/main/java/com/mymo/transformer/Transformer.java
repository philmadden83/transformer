package com.mymo.transformer;

import com.mymo.transformer.configuration.*;
import com.mymo.transformer.exception.InstantiationException;
import com.mymo.transformer.exception.TransformException;

/**
 *
 * @author Phil Madden
 *
 */
public class Transformer {
	private final AbstractContext context;
	private TransformBuilder transformBuilder;

	public Transformer(Context context) {
		this.context = (AbstractContext) context;
	}

	public <T> TransformBuilder transform(T t) {
		if (transformBuilder == null) {
			transformBuilder = new TransformBuilder(t);
		} else {
			//flyweight
			transformBuilder.reset(t);
		}
		return transformBuilder;
	}

	private <T, V> V transform(T source, V target) throws TransformException {
		if (target == null) {
			target = (V) context.newInstance(target.getClass());
		}

		BeanDefinition sourceDefinition = context.getBeanDefinition(source.getClass());
		BeanDefinition targetDefinition = context.getBeanDefinition(target.getClass());

		for (BeanFieldDefinition pojoField : sourceDefinition.getBeanFieldDefinitions()) {
			transform(source, target, pojoField, targetDefinition);
		}
		return target;
	}

	private <T, V> void transform(T source, V target, BeanFieldDefinition pojoField, BeanDefinition targetPojoDefinition) throws TransformException {
		if (source != null) {
			T sourceValue = pojoField.getValue(source);
			if (sourceValue != null) {
				try {
					Object targetValue;
					if (pojoField.isComplex()) {
						targetValue = transform(pojoField.getValue(source), (V) context.newInstance(targetPojoDefinition.getBeanFieldDefinition(pojoField.getTargetFieldName()).getType()));
					} else {
						targetValue = pojoField.getTransformHandler().transform(pojoField.getValue(source));
					}

					targetPojoDefinition.getBeanFieldDefinition(pojoField.getTargetFieldName()).setValue(target, targetValue);
				} catch (InstantiationException | IllegalArgumentException e) {
					throw new TransformException(e.getMessage(), e);
				}
			}
		}
	}

	public final class TransformBuilder {
		private Object t;

		public <T> TransformBuilder(T t) {
			this.t = t;
		}

		protected <T> void reset(T t) {
			this.t = t;
		}

		public final <V> V into(V t) throws TransformException {
			return Transformer.this.transform(this.t, (V) t);
		}
	}

}
