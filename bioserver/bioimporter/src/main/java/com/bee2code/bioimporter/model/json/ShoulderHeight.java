package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Vertical distance from the floor (Basis) to the acromion
 */
@ApiModel(description = "Vertical distance from the floor (Basis) to the acromion")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class ShoulderHeight {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("e_height")
	private BigDecimal eHeight = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public ShoulderHeight dataAvailability(DataAvailability dataAvailability) {
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

	public ShoulderHeight eHeight(BigDecimal eHeight) {
		this.eHeight = eHeight;
		return this;
	}

	/**
	 * Get eHeight
	 * @return eHeight
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getEHeight() {
		return eHeight;
	}

	public void setEHeight(BigDecimal eHeight) {
		this.eHeight = eHeight;
	}

	public ShoulderHeight timestamp(MeasurementDate timestamp) {
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
		ShoulderHeight shoulderHeight = (ShoulderHeight) o;
		return Objects.equals(this.dataAvailability, shoulderHeight.dataAvailability)
				&& Objects.equals(this.eHeight, shoulderHeight.eHeight)
				&& Objects.equals(this.timestamp, shoulderHeight.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, eHeight, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ShoulderHeight {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    eHeight: ").append(toIndentedString(eHeight)).append("\n");
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
