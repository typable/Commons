package com.core.sql;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.core.model.Model;


public class Database {

	private String url;

	public Database(String url) {

		this.url = url;
	}

	public <T extends Model> TypedQuery<T> query(String sql, Class<T> type) {

		try(Connection connection = DriverManager.getConnection(url)) {

			Statement statement = connection.createStatement();

			ResultSet set = statement.executeQuery(sql);

			if(set != null) {

				TypedQuery<T> query = new TypedQuery<>();
				ResultSetMetaData meta = set.getMetaData();

				while(set.next()) {

					T model = type.getConstructor().newInstance();

					int count = meta.getColumnCount();

					for(int i = 1; i < count + 1; i++) {

						String name = meta.getColumnName(i);

						for(Field field : type.getSuperclass().getDeclaredFields()) {

							if(field.getName().equals(name)) {

								Object value = field.getType().cast(set.getObject(name));

								field.setAccessible(true);
								field.set(model, value);
							}
						}

						for(Field field : type.getDeclaredFields()) {

							if(field.getName().equals(name)) {

								Object value = field.getType().cast(set.getObject(name));

								field.setAccessible(true);
								field.set(model, value);
							}
						}
					}

					query.add(model);
				}

				return query;
			}
		}
		catch(Exception ex) {

			ex.printStackTrace();
		}

		return null;
	}

	public boolean execute(String sql) {

		try(Connection connection = DriverManager.getConnection(url)) {

			Statement statement = connection.createStatement();

			return statement.execute(sql);
		}
		catch(SQLException ex) {

			ex.printStackTrace();
		}

		return false;
	}

	public String getUrl() {

		return url;
	}
}
