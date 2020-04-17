package com.core.service;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import com.core.model.Model;
import com.core.sql.Database;
import com.core.sql.TypedQuery;


public class ModelService
{
	private Database database;

	public ModelService(Database database)
	{
		this.database = database;
	}

	public <T extends Model> boolean save(String table, T model)
	{
		try
		{
			Map<String, String> map = extract(model);

			if(model.getId() != null)
			{
				return database.execute("INSERT INTO " + table + " (id, " + String
				      .join(", ", map.keySet()) + ") VALUES ('" + model
				            .getId() + "', " + String.join(", ", map.values()) + ")");
			}
			else
			{
				return database.execute("INSERT INTO " + table + " (" + String.join(", ", map
				      .keySet()) + ") VALUES ('" + String.join(", ", map.values()) + ")");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return false;
	}

	public <T extends Model> boolean update(String table, T model)
	{
		try
		{
			Map<String, String> map = extract(model);

			String updates = "";

			for(String key : map.keySet())
			{
				updates += key + " = " + map.get(key) + ", ";
			}

			updates = updates.substring(0, updates.lastIndexOf(", "));

			return database.execute("UPDATE " + table + " SET " + updates + " WHERE id LIKE '" + model
			      .getId() + "'");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return false;
	}

	public <T extends Model> T get(String table, String id, Class<T> type)
	{
		TypedQuery<T> query = database
		      .query("SELECT * FROM " + table + " WHERE id LIKE '" + id + "'", type);

		if(query.next())
		{
			return query.get();
		}

		return null;
	}

	public boolean delete(String table, String id)
	{
		return database.execute("DELETE FROM " + table + " WHERE id LIKE '" + id + "'");
	}

	public <T extends Model> boolean has(String table, String id, Class<T> type)
	{
		return get(table, id, type) != null;
	}

	private <T extends Model> Map<String, String> extract(T model) throws Exception
	{
		Map<String, String> map = new LinkedHashMap<>();

		for(Field field : model.getClass().getDeclaredFields())
		{
			field.setAccessible(true);

			Object obj = field.get(model);

			if(obj != null)
			{
				String value = null;

				if(obj instanceof String)
				{
					value = "'" + obj.toString() + "'";
				}
				else
				{
					value = obj.toString();
				}

				map.put(field.getName(), value);
			}
		}

		return map;
	}

	public Database getDatabase()
	{
		return database;
	}
}
