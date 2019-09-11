package com.core.service;

import com.core.sql.Database;


public class DatabaseService extends Service {

	private Database database;

	public DatabaseService(String url) {

		database = new Database(url);
	}

	public Database getDatabase() {

		return database;
	}

	public void setDatabase(Database database) {

		this.database = database;
	}
}
