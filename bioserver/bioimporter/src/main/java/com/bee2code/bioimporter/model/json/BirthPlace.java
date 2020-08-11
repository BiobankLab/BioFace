package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Place of birth
 */
@ApiModel(description = "Place of birth")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class BirthPlace {
	@JsonProperty("BP")
	private String BP = null;

	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	public BirthPlace BP(String BP) {
		this.BP = BP;
		return this;
	}

	/**
	 * place of birth
	 * @return BP
	**/
	@ApiModelProperty(value = "place of birth")

	public String getBP() {
		return BP;
	}

	public void setBP(String BP) {
		this.BP = BP;
	}

	public BirthPlace dataAvailability(DataAvailability dataAvailability) {
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
		BirthPlace birthPlace = (BirthPlace) o;
		return Objects.equals(this.BP, birthPlace.BP)
				&& Objects.equals(this.dataAvailability, birthPlace.dataAvailability);
	}

	@Override
	public int hashCode() {
		return Objects.hash(BP, dataAvailability);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BirthPlace {\n");

		sb.append("    BP: ").append(toIndentedString(BP)).append("\n");
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
