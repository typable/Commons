package com.core.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {

	private String name;
	private Connection connection;

	public Database(SQLite sqlite, String name) throws SQLException {

		this.name = name;

		connection = DriverManager.getConnection(sqlite.getUrl() + "/" + name + ".db");
	}

	public ResultSet query(String query) throws SQLException {

		return new Query(connection, null, "").execute(query);
	}

	public String getName() {

		return name;
	}

	public Connection getConnection() {

		return connection;
	}
}
