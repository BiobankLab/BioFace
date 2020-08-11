package com.dispersion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiobankForDispersion {
		
	@JsonProperty("biobankName")
	private String biobankName;

	@JsonProperty("biobankKey")
	private String biobankKey;
	
	@JsonProperty("token")
	private String token;

	public String getBiobankName() {
		return biobankName;
	}
	
	@JsonProperty("biobankName")
	public void setBiobankName(String biobankName) {
		this.biobankName = biobankName;
	}

	public String getBiobankKey() {
		return biobankKey;
	}

	@JsonProperty("biobankKey")
	public void setBiobankKey(String biobankKey) {
		this.biobankKey = biobankKey;
	}
	
	public String getToken() {
		return token;
	}

	@JsonProperty("token")
	public void setToken(String token) {
		this.token = token;
	}

}
