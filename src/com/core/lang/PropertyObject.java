package com.core.lang;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class PropertyObject
{
	private Map<String, Object> map;

	public PropertyObject()
	{
		map = new LinkedHashMap<>();
	}

	public String getString(String key)
	{
		return get(key, String.class);
	}

	public Integer getInt(String key)
	{
		return get(key, Integer.class);
	}

	public Double getDouble(String key)
	{
		return get(key, Double.class);
	}

	public Boolean getBoolean(String key)
	{
		return get(key, Boolean.class);
	}

	public Float getFloat(String key)
	{
		return get(key, Float.class);
	}

	public Long getLong(String key)
	{
		return get(key, Long.class);
	}

	public Object get(String key)
	{
		return map.get(key);
	}

	public <T> T get(String key, Class<T> type)
	{
		return type.cast(map.get(key));
	}

	public void add(String key, Object value)
	{
		map.put(key, value);
	}

	public boolean has(String key)
	{
		return map.containsKey(key);
	}

	public Set<String> keys()
	{
		return map.keySet();
	}
}
