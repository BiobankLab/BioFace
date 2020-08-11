package com.bioface.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectHistory {

	@JsonProperty
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date executedAt;

	@JsonProperty
	private Long rowsNum;
	
	@JsonProperty
	private Long rowsNumDisp;

	@JsonProperty
	private List<SampleResult> result;

	public Date getExecutedAt() {
		return executedAt;
	}

	public void setExecutedAt(Date executedAt) {
		this.executedAt = executedAt;
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

	public List<SampleResult> getResult() {
		return result;
	}

	public void setResult(List<SampleResult> result) {
		this.result = result;
	}

}
