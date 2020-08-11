package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Ethnic origin of donor
 */
@ApiModel(description = "Ethnic origin of donor")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class EthnicGroup {
	/**
	 * Gets or Sets egName
	 */
	public enum EgNameEnum {
	EUROPEAN_ORIGIN("European origin"),

	ASIAN_ORIGIN("Asian origin"),

	AFRICAN_ORIGIN("African origin"),

	NORTH_AMERICAN_ORIGIN("North American origin"),

	SOUTH_AMERICAN_ORIGIN("South American origin"),

	AUSTRALIAN_ORIGIN("Australian origin"),

	MIXED("Mixed");

		private String value;

		EgNameEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static EgNameEnum fromValue(String text) {
			for (EgNameEnum b : EgNameEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("EG_name")
	private EgNameEnum egName = null;

	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	public EthnicGroup egName(EgNameEnum egName) {
		this.egName = egName;
		return this;
	}

	/**
	 * Get egName
	 * @return egName
	**/
	@ApiModelProperty(value = "")

	public EgNameEnum getEgName() {
		return egName;
	}

	public void setEgName(EgNameEnum egName) {
		this.egName = egName;
	}

	public EthnicGroup dataAvailability(DataAvailability dataAvailability) {
		this.dataAvailability = dataAvailability;
		return this;
	}

	/**
	 * Get dataAvailability
	 * @return dataAvailability
	**/
	@ApiModelProperty(value = "")

	@Valid

	public DataAvailability getDataAvailability() {
		return dataAvailability;
	}

	public void setDataAvailability(DataAvailability dataAvailability) {
		this.dataAvailability = dataAvailability;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EthnicGroup ethnicGroup = (EthnicGroup) o;
		return Objects.equals(this.egName, ethnicGroup.egName)
				&& Objects.equals(this.dataAvailability, ethnicGroup.dataAvailability);
	}

	@Override
	public int hashCode() {
		return Objects.hash(egName, dataAvailability);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class EthnicGroup {\n");

		sb.append("    egName: ").append(toIndentedString(egName)).append("\n");
		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
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
