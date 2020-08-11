package com.bioface.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets EnumSex
 */
public enum EnumSex {

	MALE("Male"),

	FEMALE("Female"),

	UNKNOWN("Unknown"),

	UNDIFFERENTIATED("Undifferentiated");

	private String value;

	EnumSex(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static EnumSex fromValue(String text) {
		for (EnumSex b : EnumSex.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
