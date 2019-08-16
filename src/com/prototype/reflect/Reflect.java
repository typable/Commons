package com.prototype.reflect;

import com.prototype.type.Property;


public class Reflect {

	public static Object newInstance(Class<?> a) throws Exception {

		return a.getConstructor().newInstance();
	}

	public static <T> void inject(T a, Property<? extends Object> args) throws Exception {

		//
	}
}
