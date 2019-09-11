package com.core.populate;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.core.model.Model;


public class ModelPopulator<T extends Model> extends AutoPopulator<T> {

	@Override
	public void populate(T target, ResultSet source) {

		try {

			target.setId(source.getString("id"));

			super.populate(target, source);
		}
		catch(SQLException ex) {

			ex.printStackTrace();
		}
	}
}
