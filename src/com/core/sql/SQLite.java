package com.core.sql;

import java.sql.SQLException;


public class SQLite {

	private String url;

	public SQLite(String url) {

		this.url = url;
	}

	public Database database(String name) throws SQLException {

		return new Database(this, name);
	}

	public String getUrl() {

		return url;
	}
}
