package com.core.service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import com.core.model.Model;
import com.core.populate.ModelPopulator;


public class ModelService extends DatabaseService {

	public ModelService(String url) {

		super(url);
	}

	public <T extends Model> boolean save(String table, T model) {

		try {

			Map<String, String> map = extract(model);

			return getDatabase().querySet("INSERT INTO " + table + " (id, " + String.join(", ", map.keySet()) + ") VALUES ('" + model.getId() + "', " + String.join(", ", map.values()) + ")");
		}
		catch(Exception ex) {

			ex.printStackTrace();
		}

		return false;
	}

	public <T extends Model> boolean update(String table, T model) {

		try {

			Map<String, String> map = extract(model);

			String updates = "";

			for(String key : map.keySet()) {

				updates += key + " = " + map.get(key) + ", ";
			}

			updates = updates.substring(0, updates.lastIndexOf(", "));

			return getDatabase().querySet("UPDATE " + table + " SET " + updates + " WHERE id LIKE '" + model.getId() + "'");
		}
		catch(Exception ex) {

			ex.printStackTrace();
		}

		return false;
	}

	public <T extends Model> T get(String table, String id, Class<T> type) {

		T model = null;

		try(Connection connection = getDatabase().connect()) {

			Statement statement = connection.createStatement();

			ResultSet set = statement.executeQuery("SELECT * FROM " + table + " WHERE id LIKE '" + id + "'");

			if(set.next()) {

				model = type.getConstructor().newInstance();

				ModelPopulator<T> populator = new ModelPopulator<>();

				populator.populate(model, set);
			}
		}
		catch(SQLException ex) {

			ex.printStackTrace();
		}
		catch(Exception ex) {

			ex.printStackTrace();
		}

		return model;
	}

	public boolean delete(String table, String id) {

		return getDatabase().querySet("DELETE FROM " + table + " WHERE id LIKE '" + id + "'");
	}

	public <T extends Model> boolean has(String table, String id, Class<T> type) {

		return get(table, id, type) != null;
	}

	private <T extends Model> Map<String, String> extract(T model) throws Exception {

		Map<String, String> map = new LinkedHashMap<>();

		for(Field field : model.getClass().getDeclaredFields()) {

			field.setAccessible(true);

			Object obj = field.get(model);

			if(obj != null) {

				String value = null;

				if(obj instanceof String) {

					value = "'" + obj.toString() + "'";
				}
				else {

					value = obj.toString();
				}

				map.put(field.getName(), value);
			}
		}

		return map;
	}
}
