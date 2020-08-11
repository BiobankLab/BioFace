package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * place of current residence
 */
@ApiModel(description = "place of current residence")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class ResidencePlace {
	@JsonProperty("RP")
	private String RP = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	public ResidencePlace RP(String RP) {
		this.RP = RP;
		return this;
	}

	/**
	 * place of current residence
	 * @return RP
	**/
	@ApiModelProperty(value = "place of current residence")

	public String getRP() {
		return RP;
	}

	public void setRP(String RP) {
		this.RP = RP;
	}

	public ResidencePlace timestamp(MeasurementDate timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	/**
	 * Get timestamp
	 * @return timestamp
	**/
	@ApiModelProperty(value = "")

	@Valid

	public MeasurementDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(MeasurementDate timestamp) {
		this.timestamp = timestamp;
	}

	public ResidencePlace dataAvailability(DataAvailability dataAvailability) {
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
		ResidencePlace residencePlace = (ResidencePlace) o;
		return Objects.equals(this.RP, residencePlace.RP) && Objects.equals(this.timestamp, residencePlace.timestamp)
				&& Objects.equals(this.dataAvailability, residencePlace.dataAvailability);
	}

	@Override
	public int hashCode() {
		return Objects.hash(RP, timestamp, dataAvailability);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResidencePlace {\n");

		sb.append("    RP: ").append(toIndentedString(RP)).append("\n");
		sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
