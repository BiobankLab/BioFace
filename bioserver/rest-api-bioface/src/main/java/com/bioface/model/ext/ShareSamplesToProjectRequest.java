package com.bioface.model.ext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShareSamplesToProjectRequest {

	@JsonProperty
	private String projectId;

	@JsonProperty
	private String biobank;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getBiobank() {
		return biobank;
	}

	public void setBiobank(String biobank) {
		this.biobank = biobank;
	}

}
