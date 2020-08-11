package com.bee2code.bioimporter.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum KellEnum {
	K("K"),

	k("k"),

	KP("kp");

	private String value;

	KellEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static KellEnum fromValue(String text) {
		for (KellEnum b : KellEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
