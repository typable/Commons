package com.core.base;

public class Condition {

	public static <T> boolean isNotNull(T a) {

		return a != null;
	}

	/*
	@SafeVarargs
	public static <T> boolean isNotNull(T... a) {
	
		for(T t : a) {
	
			if(isNull(t)) {
	
				return false;
			}
		}
	
		return true;
	}
	*/

	public static <T> boolean isNull(T a) {

		return a == null;
	}

	/*
	@SafeVarargs
	public static <T> boolean isNull(T... a) {
	
		for(T t : a) {
	
			if(isNotNull(t)) {
	
				return false;
			}
		}
	
		return true;
	}
	*/

	public static boolean isTrue(Boolean a) {

		return a == true;
	}

	public static boolean isFalse(Boolean a) {

		return a == false;
	}

	public static boolean isNotBlank(String a) {

		return isNotNull(a) && !a.isBlank();
	}

	public static boolean isBlank(String a) {

		return isNotNull(a) && a.isBlank();
	}

	public static boolean isNotEmpty(String a) {

		return isNotNull(a) && !a.isEmpty();
	}

	public static boolean isEmpty(String a) {

		return isNotNull(a) && a.isEmpty();
	}

	public static boolean equals(String a, String b) {

		return isNotNull(b) && isNotNull(b) && a.equals(b);
	}
}
