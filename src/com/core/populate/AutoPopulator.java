package com.core.populate;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AutoPopulator<T> implements Populator<T, ResultSet>
{
	@Override
	public void populate(T target, ResultSet source)
	{
		Class<?> instance = target.getClass();

		for(Method method : instance.getDeclaredMethods())
		{
			String name = method.getName();

			if(name.startsWith("set"))
			{
				String key = name.substring(3, name.length()).toLowerCase();

				Class<?> type = method.getParameterTypes()[0];

				try
				{
					method.invoke(target, type.cast(source.getObject(key)));
				}
				catch(SQLException ex)
				{
					ex.printStackTrace();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
}
