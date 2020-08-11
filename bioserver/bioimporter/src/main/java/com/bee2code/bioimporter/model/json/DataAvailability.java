package com.bee2code.bioimporter.model.json;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets data_availability
 */
public enum DataAvailability {

	YES("yes"),

	NO("no");

	private String value;

	DataAvailability(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	//@JsonCreator
	public static DataAvailability fromValue(String text) {
		for (DataAvailability b : DataAvailability.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
