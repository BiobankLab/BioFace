package com.bioface.model.ext;

import java.util.List;

import com.bioface.model.EnumSampleType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateProjectRequest {

	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty
	private List<EnumSampleType> sampleType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EnumSampleType> getSampleType() {
		return sampleType;
	}

	public void setSampleType(List<EnumSampleType> sampleType) {
		this.sampleType = sampleType;
	}

}
