package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Declarative total body height given in centimeters (accuracy to 0.5 cm)
 */
@ApiModel(description = "Declarative total body height given in centimeters (accuracy to 0.5 cm)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class StatureDeclarative {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("stature")
	private BigDecimal stature = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public StatureDeclarative dataAvailability(DataAvailability dataAvailability) {
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

	public StatureDeclarative stature(BigDecimal stature) {
		this.stature = stature;
		return this;
	}

	/**
	 * Get stature
	 * @return stature
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getStature() {
		return stature;
	}

	public void setStature(BigDecimal stature) {
		this.stature = stature;
	}

	public StatureDeclarative timestamp(MeasurementDate timestamp) {
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		StatureDeclarative statureDeclarative = (StatureDeclarative) o;
		return Objects.equals(this.dataAvailability, statureDeclarative.dataAvailability)
				&& Objects.equals(this.stature, statureDeclarative.stature)
				&& Objects.equals(this.timestamp, statureDeclarative.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, stature, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class StatureDeclarative {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    stature: ").append(toIndentedString(stature)).append("\n");
		sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
