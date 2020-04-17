package com.core.parse;

import com.core.type.CSVObject;


public class CSVParser implements Parser<CSVObject, String>
{
	private String delimiter = ",";

	@Override
	public CSVObject parse(String source)
	{
		CSVObject target = null;

		String[] args = source.split("\r\n");

		String[] keys = args[0].replaceAll("\\s", "").split(delimiter);

		target = new CSVObject(keys);

		for(int i = 1; i < args.length; i++)
		{
			String[] values = args[i].replaceAll("\\s", "").split(delimiter);

			target.add((Object[]) values);
		}

		return target;
	}

	@Override
	public String compose(CSVObject source)
	{
		String target = "";

		for(String key : source.getMap().keys())
		{
			target += key + delimiter;
		}

		target += "\r\n";

		for(int i = 0; i < source.size(); i++)
		{
			Object[] array = source.get(i);

			for(Object obj : array)
			{
				if(obj == null)
				{
					target += ";";
				}
				else
				{
					target += String.valueOf(obj) + delimiter;
				}
			}

			target += "\r\n";
		}

		return target;
	}

	public String getDelimiter()
	{
		return delimiter;
	}

	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}
}
