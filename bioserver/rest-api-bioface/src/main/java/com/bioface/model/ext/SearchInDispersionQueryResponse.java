package com.bioface.model.ext;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchInDispersionQueryResponse {

	@JsonProperty
	private String name;

	@JsonProperty
	private String result;

	@JsonProperty
	private boolean isError;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

}
