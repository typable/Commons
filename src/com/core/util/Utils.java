package com.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.core.lang.Property;


public class Utils {

	public static void keySet(Property<String> attributes, String separator, String code) {

		if(code.contains(separator)) {

			String[] parts = code.split(separator);

			attributes.put(parts[0], parts[1]);
		}
	}

	public static void addAttribute(Property<String> property, String delimiter, String code) {

		if(code.contains(delimiter)) {

			String[] args = code.split(delimiter, -1);

			property.put(args[0], args[1]);
		}
	}

	/*
	public static void addParameter(Property<Parameter> params, String delimiter, String code) {
	
		if(code.contains(delimiter)) {
	
			String[] args = code.split(delimiter, -1);
	
			Parameter param = new Parameter(args[0]);
			param.setValue(args[1]);
	
			params.put(args[0], param);
		}
	}
	*/

	public static String encode(String value) {

		return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.ISO_8859_1));
	}
}
