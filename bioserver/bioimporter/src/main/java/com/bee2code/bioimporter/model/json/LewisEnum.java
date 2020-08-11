package com.bee2code.bioimporter.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LewisEnum {
	AMinusBMinus("Le(a-b-)"),

	AMinusBPlus("Le(a-b+)"),

	APlusBMinus("Le(a+b-)");

	private String value;

	LewisEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static LewisEnum fromValue(String text) {
		for (LewisEnum b : LewisEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}