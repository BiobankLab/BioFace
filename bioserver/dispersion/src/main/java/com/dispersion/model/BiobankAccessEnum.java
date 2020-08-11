package com.dispersion.model;

public enum BiobankAccessEnum {

	ONLY_PUBLIC("ONLY_PUBLIC"), PUBLIC_PROTECTED("PUBLIC_PROTECTED"), ALL("ALL");

	private String name;

	private BiobankAccessEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
