package com.bioface.model.ext;

public class UserRolesResponse {

	private boolean admin = false;

	private boolean biobank = false;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isBiobank() {
		return biobank;
	}

	public void setBiobank(boolean biobank) {
		this.biobank = biobank;
	}

}
