package com.core.parse;

import java.util.ArrayList;
import java.util.List;

import com.core.type.JSONObject;


public class JSONParser implements Parser<JSONObject, String> {

	@Override
	public JSONObject parse(String source) {

		JSONObject target = new JSONObject();

		for(String s : splitJSON(source)) {

			String key = s.substring(1, s.indexOf(": ") - 1);
			String value = s.substring(s.indexOf(": ") + 2);

			/** JSONObject **/
			if(value.matches("\\{.*\\}")) {

				target.add(key, parse(value));
			}

			/** JSONObject[] **/
			if(value.matches("\\[.*\\]")) {

				List<JSONObject> oList = new ArrayList<>();

				for(String ss : splitJSON(value)) {

					oList.add(parse(ss));
				}

				target.add(key, oList.toArray(new JSONObject[oList.size()]));
			}

			/** String **/
			if(value.matches("\".*\"")) {

				target.add(key, value.substring(1, value.length() - 1));
			}

			/** Character **/
			if(value.matches("'.'")) {

				target.add(key, value.charAt(1));
			}

			/** Double **/
			if(value.matches("[\\d.]+D")) {

				target.add(key, Double.valueOf(value));
			}

			/** Float **/
			if(value.matches("[\\d.]+F")) {

				target.add(key, Float.valueOf(value));
			}

			/** Long **/
			if(value.matches("[\\d]+L")) {

				target.add(key, Long.valueOf(value.substring(0, value.length() - 1)));
			}

			/** Boolean **/
			if(value.matches("(true|false)")) {

				target.add(key, Boolean.valueOf(value));
			}
		}

		return target;
	}

	@Override
	public String compose(JSONObject source) {

		List<String> childList = new ArrayList<>();

		for(String key : source.keys()) {

			Object obj = source.get(key);
			String json = "";

			if(obj != null) {

				json += "\"" + key + "\": ";

				if(obj instanceof JSONObject) {

					json += compose((JSONObject) obj);
				}
				else if(obj instanceof JSONObject[]) {

					List<String> subList = new ArrayList<>();

					for(JSONObject o : (JSONObject[]) obj) {

						if(o != null) {

							subList.add(compose(o));
						}
					}

					json += "[" + String.join(", ", subList) + "]";
				}
				else if(obj instanceof String) {

					json += "\"" + obj.toString() + "\"";
				}
				else if(obj instanceof Character) {

					json += "'" + obj.toString() + "'";
				}
				else if(obj instanceof Boolean) {

					json += obj.toString();
				}
				else if(obj instanceof Integer) {

					json += obj.toString();
				}
				else if(obj instanceof Double) {

					json += obj.toString() + "D";
				}
				else if(obj instanceof Float) {

					json += obj.toString() + "F";
				}
				else if(obj instanceof Long) {

					json += obj.toString() + "L";
				}
				else {

					json += "null";
				}

				childList.add(json);
			}
		}

		return "{" + String.join(", ", childList) + "}";
	}

	private String[] splitJSON(String source) {

		List<String> list = new ArrayList<>();
		String sub = "";
		boolean text = false;
		boolean object = false;
		boolean array = false;

		char[] arr = source.toCharArray();

		for(int i = 1; i < arr.length - 1; i++) {

			char c = arr[i];

			if(c == '{') {

				object = true;
			}

			if(c == '}') {

				object = false;
			}

			if(c == '[') {

				array = true;
			}

			if(c == ']') {

				array = false;
			}

			if(c == '"') {

				text = text ? false : true;
			}

			if(!object && !array && !text && c == ',') {

				list.add(sub);
				sub = "";

				i++;
			}
			else {

				sub += c;
			}
		}

		list.add(sub);

		return list.toArray(new String[list.size()]);
	}
}
