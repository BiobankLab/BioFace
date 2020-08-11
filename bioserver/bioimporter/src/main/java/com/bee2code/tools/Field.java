package com.bee2code.tools;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Field {

	@JsonProperty
	private String fieldName;

	@JsonProperty
	private String fieldType;

	@JsonProperty
	private boolean multiValued;

	@JsonProperty
	private boolean stored;

	@JsonProperty
	private boolean indexed;
}
