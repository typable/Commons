package com.core.sql;

import java.sql.SQLException;
import java.sql.Statement;


public interface QueryRunnable {

	public void run(Statement statement) throws SQLException;
}
