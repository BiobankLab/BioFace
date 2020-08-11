package com.bioface.model.ext;

import java.util.List;

import com.bioface.model.EnumSampleType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateProjectRequest {

	@JsonProperty
	private String id;

	@JsonProperty
	private String description;

	@JsonProperty
	private List<EnumSampleType> sampleType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
