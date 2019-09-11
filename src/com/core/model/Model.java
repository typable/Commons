package com.core.model;

public class Model {

	private String id;

	public boolean isValid() {

		return id != null && id.length() > 0;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}
}
