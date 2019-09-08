package com.core.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLite {

	private String url;

	public SQLite(String url) {

		this.url = url;
	}

	public void query(QueryRunnable runnable) {

		try(Connection connection = DriverManager.getConnection(url)) {

			runnable.run(connection.createStatement());
		}
		catch(SQLException ex) {

			ex.printStackTrace();
		}
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}
}