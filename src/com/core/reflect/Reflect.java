package com.core.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.core.lang.Property;


/**
 * @version 1.3.4
 * @author Prototype Studio
 */
public class Reflect
{
	/**
	 * Creates a new instance of a class
	 * 
	 * @param type Class to create a new instance;
	 * @return New instance of class
	 * @throws Exception
	 */
	public static Object newInstance(Class<?> type) throws Exception
	{
		return type.getConstructor().newInstance();
	}

	/**
	 * Inject all fields in the class by the given properties
	 * 
	 * @param type Instance to inject
	 * @param args List of injectable objects
	 * @throws Exception
	 */
	public static <T> void inject(T type, Property<Object> args) throws Exception
	{
		if(type instanceof Injectable)
		{
			for(Field field : type.getClass().getDeclaredFields())
			{
				for(Annotation annotation : field.getAnnotations())
				{
					if(annotation instanceof Inject)
					{
						for(String key : args.keys())
						{
							if(((Inject) annotation).code().equals(key))
							{
								field.setAccessible(true);
								field.set(type, args.get(key));
							}
						}
					}
				}
			}
		}
	}
}
