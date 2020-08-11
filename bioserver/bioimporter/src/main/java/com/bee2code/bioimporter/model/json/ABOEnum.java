package com.bee2code.bioimporter.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ABOEnum {
	A("A"),

	B("B"),

	AB("AB"),

	_0("0");

	private String value;

	ABOEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ABOEnum fromValue(String text) {
		for (ABOEnum b : ABOEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}