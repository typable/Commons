package com.core.lang;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;


public class Property<T extends Object> {

	private LinkedHashMap<String, T> map;

	public Property() {

		map = new LinkedHashMap<String, T>();
	}

	public boolean has(String key) {

		return map.containsKey(key);
	}

	public int size() {

		return map.size();
	}

	public boolean isEmpty() {

		return map.isEmpty();
	}

	public void put(String key, T value) {

		map.put(key, value);
	}

	public T get(String key) {

		return get(key, false);
	}

	public T get(String key, boolean required) {

		T val = map.get(key);

		if(required && val == null) {

			throw new IllegalArgumentException("The argument '" + key + "' is required!");
		}

		return val;
	}

	public <V> V get(String key, Class<V> type) {

		T t = map.get(key);
		Object val = t;
		V obj = null;

		if(type != null && val != null) {

			try {

				if(t.getClass() == String.class) {

					if(type == Integer.class) {

						val = Integer.parseInt(val.toString());
					}

					if(type == Boolean.class) {

						val = Boolean.parseBoolean(val.toString());
					}
				}

				obj = type.cast(val);
			}
			catch(ClassCastException ex) {

				throw new IllegalArgumentException("The argument '" + key + "' is malformed!");
			}
		}

		return obj;
	}

	public <V> V get(String key, Class<V> type, boolean required) {

		T t = map.get(key);
		Object val = t;
		V obj = null;

		if(required && val == null) {

			throw new IllegalArgumentException("The argument '" + key + "' is required!");
		}

		if(type != null && val != null) {

			try {

				if(t.getClass() == String.class) {

					if(type == Integer.class) {

						val = Integer.parseInt(val.toString());
					}

					if(type == Boolean.class) {

						val = Boolean.parseBoolean(val.toString());
					}
				}

				obj = type.cast(val);
			}
			catch(ClassCastException ex) {

				throw new IllegalArgumentException("The argument '" + key + "' is malformed!");
			}
		}

		return obj;
	}

	public void remove(String key) {

		map.remove(key);
	}

	public Set<String> keys() {

		return map.keySet();
	}

	public Collection<T> values() {

		return map.values();
	}
}
