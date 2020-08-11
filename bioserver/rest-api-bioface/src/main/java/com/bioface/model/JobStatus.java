package com.bioface.model;

public enum JobStatus {

	NEW("new"), ERROR("ERROR"), IN_PROGRESS("in_progress"), FINISHED("finished");

	private String value;

	private JobStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
