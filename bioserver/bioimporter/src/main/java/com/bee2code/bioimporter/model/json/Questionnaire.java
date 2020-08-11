package com.bee2code.bioimporter.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * access to questionnaire
 */
public enum Questionnaire {

	YES("yes"),

	NO("no");

	private String value;

	Questionnaire(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static Questionnaire fromValue(String text) {
		for (Questionnaire b : Questionnaire.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
