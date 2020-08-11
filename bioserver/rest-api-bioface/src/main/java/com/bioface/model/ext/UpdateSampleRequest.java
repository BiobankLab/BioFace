package com.bioface.model.ext;

import com.bioface.model.EnumAccess;

public class UpdateSampleRequest {

	private String id;

	private EnumAccess enumAccess;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EnumAccess getEnumAccess() {
		return enumAccess;
	}

	public void setEnumAccess(EnumAccess enumAccess) {
		this.enumAccess = enumAccess;
	}

}
