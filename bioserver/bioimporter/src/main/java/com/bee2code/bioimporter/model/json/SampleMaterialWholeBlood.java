package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * SampleMaterialWholeBlood
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleMaterialWholeBlood {
	/**
	 * method of blood collecion (EDTA, heparin, citrate, CPDA)
	 */
	public enum MethodEnum {
	EDTA("EDTA"),

	HEPARIN("heparin"),

	CITRATE("citrate"),

	CPDA("CPDA");

		private String value;

		MethodEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static MethodEnum fromValue(String text) {
			for (MethodEnum b : MethodEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("method")
	private MethodEnum method = null;

	public SampleMaterialWholeBlood method(MethodEnum method) {
		this.method = method;
		return this;
	}

	/**
	 * method of blood collecion (EDTA, heparin, citrate, CPDA)
	 * @return method
	**/
	@ApiModelProperty(value = "method of blood collecion (EDTA, heparin, citrate, CPDA)")

	public MethodEnum getMethod() {
		return method;
	}

	public void setMethod(MethodEnum method) {
		this.method = method;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SampleMaterialWholeBlood sampleMaterialWholeBlood = (SampleMaterialWholeBlood) o;
		return Objects.equals(this.method, sampleMaterialWholeBlood.method);
	}

	@Override
	public int hashCode() {
		return Objects.hash(method);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleMaterialWholeBlood {\n");

		sb.append("    method: ").append(toIndentedString(method)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
