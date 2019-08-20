package com.core.base;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class Environment {

	public static final Charset CHARSET = StandardCharsets.ISO_8859_1;
	public static final String CRLF = "\r\n";
	public static final String LF = "\n";

	public static boolean isDOS() {

		return System.getProperty("os.name").startsWith("Windows");
	}

	public static boolean isUNIX() {

		return !isDOS();
	}

	public static String lineBreak() {

		return isDOS() ? CRLF : LF;
	}
}
