package com.dispersion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginData {

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}
	
}
