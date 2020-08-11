package com.dispersion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
@Document(collection = "job")
public class Job {

	@JsonProperty("dbId")
	@Id
	private String dbId;

	@JsonProperty("id")
	private String id;

	@JsonProperty("filePath")
	private String filePath;

	@JsonProperty("status")
	private JobStatus status;
	
	@JsonProperty("user")
	private String user;

	@JsonProperty("originalFileName")
	private String originalFileName;

	@JsonProperty("exceptionMessage")
	private String exceptionMessage;

	@JsonProperty("addTime")
	private Long addTime;

	@JsonProperty("indexerStartTime")
	private Long indexerStartTime;

	@JsonProperty("indexerFinishTime")
	private Long indexerFinishTime;

	@JsonProperty("biobankId")
	private String biobankId;

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public Long getIndexerStartTime() {
		return indexerStartTime;
	}

	public void setIndexerStartTime(Long indexerStartTime) {
		this.indexerStartTime = indexerStartTime;
	}

	public Long getIndexerFinishTime() {
		return indexerFinishTime;
	}

	public void setIndexerFinishTime(Long indexerFinishTime) {
		this.indexerFinishTime = indexerFinishTime;
	}

	public String getBiobankId() {
		return biobankId;
	}

	public void setBiobankId(String biobankId) {
		this.biobankId = biobankId;
	}

}
