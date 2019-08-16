package com.prototype.util;

public class Condition {

	public static <T> boolean notNull(T a) {

		return a != null;
	}

	public static <T> boolean isNull(T a) {

		return a == null;
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
