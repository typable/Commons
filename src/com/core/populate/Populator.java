package com.core.populate;

public interface Populator<T, S> {

	public void populate(T target, S source);
}
