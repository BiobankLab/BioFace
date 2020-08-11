package com.bioface.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumSampleType {

	PHYSICAL_SAMPLE("Physical sample"),
	DATA_FILES("Data files"),
	ANALYSING_DATA("Analysing data");
	
	private String name;

	private EnumSampleType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
