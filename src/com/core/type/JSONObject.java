package com.core.type;

import com.core.lang.PropertyObject;
import com.core.parse.JSONParser;


public class JSONObject extends PropertyObject {

	public static JSONObject of(String source) {

		return new JSONParser().parse(source);
	}

	public JSONObject() {

		//
	}

	@Override
	public void add(String key, Object value) {

		if(!key.matches("[A-Za-z_\\S]+")) {

			throw new IllegalArgumentException("The key '" + key + "' is malformed!");
		}

		super.add(key, value);
	}

	public JSONObject getJSONObject(String key) {

		return get(key, JSONObject.class);
	}

	public JSONObject[] getJSONObjectArray(String key) {

		return get(key, JSONObject[].class);
	}

	public String stringify() {

		return new JSONParser().compose(this);
	}
}
