package com.bioface.model.ext;

import com.bioface.model.Project;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectSampleQueryResponse {

	private Project project;

	@JsonProperty
	
	private String samples;

	private Long rowsNum;
	
	private Long rowsNumDisp;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSamples() {
		return samples;
	}

	public void setSamples(String samples) {
		this.samples = samples;
	}

	public Long getRowsNum() {
		return rowsNum;
	}

	public void setRowsNum(Long rowsNum) {
		this.rowsNum = rowsNum;
	}
	
	public Long getRowsNumDisp() {
		return rowsNumDisp;
	}

	public void setRowsNumDisp(Long rowsNumDisp) {
		this.rowsNumDisp = rowsNumDisp;
	}

}
