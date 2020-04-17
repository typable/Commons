package com.core.parse;

public interface Parser<T, S>
{
	public T parse(S source);

	public S compose(T source);
}
