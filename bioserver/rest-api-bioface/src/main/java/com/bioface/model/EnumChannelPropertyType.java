package com.bioface.model;

public enum EnumChannelPropertyType {

	project("project"),
	other("other");

	private String name;

	private EnumChannelPropertyType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
