package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Vertical distance from the floor (Basis) to the tibiale
 */
@ApiModel(description = "Vertical distance from the floor (Basis) to the tibiale")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class TibialHeight {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("t_height")
	private BigDecimal tHeight = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public TibialHeight dataAvailability(DataAvailability dataAvailability) {
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

	public TibialHeight tHeight(BigDecimal tHeight) {
		this.tHeight = tHeight;
		return this;
	}

	/**
	 * Get tHeight
	 * @return tHeight
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getTHeight() {
		return tHeight;
	}

	public void setTHeight(BigDecimal tHeight) {
		this.tHeight = tHeight;
	}

	public TibialHeight timestamp(MeasurementDate timestamp) {
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
		TibialHeight tibialHeight = (TibialHeight) o;
		return Objects.equals(this.dataAvailability, tibialHeight.dataAvailability)
				&& Objects.equals(this.tHeight, tibialHeight.tHeight)
				&& Objects.equals(this.timestamp, tibialHeight.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, tHeight, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TibialHeight {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    tHeight: ").append(toIndentedString(tHeight)).append("\n");
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
