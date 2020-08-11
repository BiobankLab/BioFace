package com.bioface.service;

public enum EnumRoleType {

	BIOBANK("biobank"), PROJECT("project");

	private String name;

	private EnumRoleType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
