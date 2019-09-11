package com.core.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

	private String url;

	public Database(String url) {

		this.url = url;
	}

	public void queryGet(QueryRunnable runnable) {

		try(Connection connection = connect()) {

			runnable.run(connection.createStatement());
		}
		catch(SQLException ex) {

			ex.printStackTrace();
		}
	}

	public boolean querySet(String sql) {

		boolean state = false;

		try(Connection connection = connect()) {

			Statement statement = connection.createStatement();

			state = statement.execute(sql);
		}
		catch(SQLException ex) {

			ex.printStackTrace();
		}

		return state;
	}

	public Connection connect() throws SQLException {

		return DriverManager.getConnection(url);
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}
}
