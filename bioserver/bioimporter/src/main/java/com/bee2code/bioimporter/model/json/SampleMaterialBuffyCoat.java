package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * SampleMaterialBuffyCoat
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleMaterialBuffyCoat {
	@JsonProperty("info")
	private String info = null;

	public SampleMaterialBuffyCoat info(String info) {
		this.info = info;
		return this;
	}

	/**
	 * the content of buffy coat
	 * @return info
	**/
	@ApiModelProperty(value = "the content of buffy coat")

	@Valid

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
		SampleMaterialBuffyCoat sampleMaterialBuffyCoat = (SampleMaterialBuffyCoat) o;
		return Objects.equals(this.info, sampleMaterialBuffyCoat.info);
	}

	@Override
	public int hashCode() {
		return Objects.hash(info);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleMaterialBuffyCoat {\n");

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
