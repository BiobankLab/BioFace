package com.bee2code.bioimporter.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RhEnum {
	RHPlus("Rh+"),

	RHMinus("Rh-");

	private String value;

	RhEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static RhEnum fromValue(String text) {
		for (RhEnum b : RhEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}