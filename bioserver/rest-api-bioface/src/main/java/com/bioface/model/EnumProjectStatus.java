package com.bioface.model;

public enum EnumProjectStatus {

	NEW("NEW"), REQUESTED("REQUESTED"), CLOSED("CLOSED");

	private String name;

	private EnumProjectStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
