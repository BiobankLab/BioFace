package com.bioface.model.ext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectSampleQueryRequest {

	@JsonProperty
	private String projectId;

	@JsonProperty
	private Integer page;

	@JsonProperty
	private Integer maxRows;

	@JsonProperty
	private boolean sortDesc;

	@JsonProperty
	private String sortField;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(Integer maxRows) {
		this.maxRows = maxRows;
	}

	public boolean isSortDesc() {
		return sortDesc;
	}

	public void setSortDesc(boolean sortDesc) {
		this.sortDesc = sortDesc;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

}
