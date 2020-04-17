package com.core.reflect;

import com.core.lang.Property;


public interface Injectable
{
	public default <T> void inject(T type, Property<Object> args)
	{
		try
		{
			Reflect.inject(type, args);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
