package com.core.facade;

import com.core.service.ModelService;


public abstract class Facade<T>
{
	private ModelService modelService;
	private String table;

	public Facade(ModelService modelService, String table)
	{
		this.modelService = modelService;
		this.table = table;
	}

	public abstract boolean save(String id, T data);

	public abstract boolean update(T data);

	public abstract T get(String id);

	public boolean delete(String id)
	{
		return modelService.delete(table, id);
	}

	public abstract boolean has(String id);

	public ModelService getModelService()
	{
		return modelService;
	}

	public String getTable()
	{
		return table;
	}
}
