package com.core.base;

public class Condition {

	public static <T> boolean notNull(T a) {

		return a != null;
	}

	@SafeVarargs
	public static <T> boolean notNull(T... a) {

		for(T t : a) {

			if(isNull(t)) {

				return false;
			}
		}

		return true;
	}

	public static <T> boolean isNull(T a) {

		return a == null;
	}

	@SafeVarargs
	public static <T> boolean isNull(T... a) {

		for(T t : a) {

			if(notNull(t)) {

				return false;
			}
		}

		return true;
	}

	public static boolean isTrue(Boolean a) {

		return a == true;
	}

	public static boolean isFalse(Boolean a) {

		return a == false;
	}

	public static boolean notBlank(String a) {

		return notNull(a) && !a.isBlank();
	}

	public static boolean isBlank(String a) {

		return notNull(a) && a.isBlank();
	}

	public static boolean notEmpty(String a) {

		return notNull(a) && !a.isEmpty();
	}

	public static boolean isEmpty(String a) {

		return notNull(a) && a.isEmpty();
	}

	public static boolean equals(String a, String b) {

		return notNull(a) && notNull(b) && a.equals(b);
	}
}
