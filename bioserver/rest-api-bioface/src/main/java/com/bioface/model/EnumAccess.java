package com.bioface.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets EnumAccess
 */
public enum EnumAccess {

	ACCESSIBLE("accessible"),

	INACCESSIBLE("inaccessible"),

	LIMITED_ACCESS("limited access");

	private String value;

	EnumAccess(String value) {
		this.value = value;
	}

	@JsonCreator
	public static EnumAccess fromValue(String text) {
		for (EnumAccess b : EnumAccess.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
