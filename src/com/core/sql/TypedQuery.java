package com.core.sql;

import java.util.ArrayList;
import java.util.List;

import com.core.lang.Loopable;
import com.core.model.Model;


public class TypedQuery<T extends Model> implements Loopable<T> {

	private List<T> list;
	private int i;

	public TypedQuery() {

		list = new ArrayList<>();
		i = -1;
	}

	public void add(T t) {

		list.add(t);
	}

	public int size() {

		return list.size();
	}

	@Override
	public boolean next() {

		i++;

		return list.size() > i;
	}

	@Override
	public T get() {

		return list.get(i);
	}
}
