package com.core.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Query {

	private Connection connection;
	private String table;
	private String command;

	public Query(Connection connection, String table, String command) {

		this.connection = connection;
		this.table = table;
		this.command = command;
	}

	public ResultSet execute(String query) throws SQLException {

		Statement statement = connection.createStatement();

		try {

			return statement.executeQuery(command + " " + query);
		}
		catch(SQLException ex) {

			if(!ex.getMessage().equals("query does not return ResultSet")) {

				ex.printStackTrace();
			}
		}

		return null;
	}

	public Connection getConnection() {

		return connection;
	}

	public String getCommand() {

		return command;
	}

	public String getTable() {

		return table;
	}
}
