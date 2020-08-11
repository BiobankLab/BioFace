package com.bioface.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "project")
public class Project {

	@Id
	@JsonProperty
	private String id;

	@JsonProperty
	private String userId;

	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty
	private List<String> queries;

	@JsonProperty
	private EnumProjectStatus status;

	@JsonIgnore
	private List<String> rolesIncluded;

	@JsonProperty
	private List<String> biobankShared;

	@JsonProperty
	private List<EnumSampleType> sampleType;

	@JsonProperty
	private List<ProjectHistory> projectHistory;

	/**
	 * Property not mapped to database
	 */
	@Transient
	private EnumProjectAccessType projectAccessType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public List<String> getQueries() {
		return queries;
	}

	public void setQueries(List<String> queries) {
		this.queries = queries;
	}

	public EnumProjectStatus getStatus() {
		return status;
	}

	public void setStatus(EnumProjectStatus status) {
		this.status = status;
	}

	public List<EnumSampleType> getSampleType() {
		return sampleType;
	}

	public void setSampleType(List<EnumSampleType> sampleType) {
		this.sampleType = sampleType;
	}

	public List<ProjectHistory> getProjectHistory() {
		return projectHistory;
	}

	public void setProjectHistory(List<ProjectHistory> projectHistory) {
		this.projectHistory = projectHistory;
	}

	public List<String> getBiobankShared() {
		return biobankShared;
	}

	public void setBiobankShared(List<String> biobankShared) {
		this.biobankShared = biobankShared;
	}

	public List<String> getRolesIncluded() {
		return rolesIncluded;
	}

	public void setRolesIncluded(List<String> rolesIncluded) {
		this.rolesIncluded = rolesIncluded;
	}

	public EnumProjectAccessType getProjectAccessType() {
		return projectAccessType;
	}

	public void setProjectAccessType(EnumProjectAccessType projectAccessType) {
		this.projectAccessType = projectAccessType;
	}

}
