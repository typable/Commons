package com.core.populate;

import java.lang.reflect.Field;

import com.core.model.Model;
import com.core.type.JSONObject;


public class JSONPopulator implements Populator<JSONObject, Model> {

	@Override
	public void populate(JSONObject target, Model source) {

		Class<? extends Model> type = source.getClass();

		try {

			for(Field field : type.getSuperclass().getDeclaredFields()) {

				field.setAccessible(true);
				target.add(field.getName(), field.get(source));
			}

			for(Field field : type.getDeclaredFields()) {

				field.setAccessible(true);
				target.add(field.getName(), field.get(source));
			}
		}
		catch(Exception ex) {

			ex.printStackTrace();
		}
	}
}
