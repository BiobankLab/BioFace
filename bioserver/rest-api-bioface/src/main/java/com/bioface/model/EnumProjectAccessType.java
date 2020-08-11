package com.bioface.model;

public enum EnumProjectAccessType {

	OWNER("OWNER"), MEMBER("MEMBER"), BIOBANK("BIOBANK");

	private String value;

	private EnumProjectAccessType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
