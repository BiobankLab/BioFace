package com.dispersion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiobankPermissionModel {

	@JsonProperty("biobank")
	private String biobank;

	@JsonProperty("permission")
	private String permission;

	public String getBiobank() {
		return biobank;
	}
	
	@JsonProperty("biobank")
	public void setBiobank(String biobank) {
		this.biobank = biobank;
	}

	public String getPermission() {
		return permission;
	}

	@JsonProperty("permission")
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
