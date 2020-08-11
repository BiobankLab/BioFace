package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * SampleMaterialSaliva
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleMaterialSaliva {
	@JsonProperty("info")
	private String info = null;

	@JsonProperty("methods")
	private String methods = null;

	public SampleMaterialSaliva info(String info) {
		this.info = info;
		return this;
	}

	/**
	 * (colour, consistency)
	 * @return info
	**/
	@ApiModelProperty(value = "(colour, consistency)")

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public SampleMaterialSaliva methods(String methods) {
		this.methods = methods;
		return this;
	}

	/**
	 * (saliva collection)
	 * @return methods
	**/
	@ApiModelProperty(value = "(saliva collection)")

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SampleMaterialSaliva sampleMaterialSaliva = (SampleMaterialSaliva) o;
		return Objects.equals(this.info, sampleMaterialSaliva.info)
				&& Objects.equals(this.methods, sampleMaterialSaliva.methods);
	}

	@Override
	public int hashCode() {
		return Objects.hash(info, methods);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleMaterialSaliva {\n");

		sb.append("    info: ").append(toIndentedString(info)).append("\n");
		sb.append("    methods: ").append(toIndentedString(methods)).append("\n");
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
