package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * SampleMaterialStool
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleMaterialStool {
	@JsonProperty("info")
	private String info = null;

	public SampleMaterialStool info(String info) {
		this.info = info;
		return this;
	}

	/**
	 * (any information)
	 * @return info
	**/
	@ApiModelProperty(value = "(any information)")

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SampleMaterialStool sampleMaterialStool = (SampleMaterialStool) o;
		return Objects.equals(this.info, sampleMaterialStool.info);
	}

	@Override
	public int hashCode() {
		return Objects.hash(info);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleMaterialStool {\n");

		sb.append("    info: ").append(toIndentedString(info)).append("\n");
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
