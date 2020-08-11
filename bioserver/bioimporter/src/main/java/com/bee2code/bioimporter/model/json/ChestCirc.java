package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Chest circumference measured through xiphoidale (xi) point
 */
@ApiModel(description = "Chest circumference measured through xiphoidale (xi) point")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class ChestCirc {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("c_circ")
	private BigDecimal cCirc = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public ChestCirc dataAvailability(DataAvailability dataAvailability) {
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

	public ChestCirc cCirc(BigDecimal cCirc) {
		this.cCirc = cCirc;
		return this;
	}

	/**
	 * Get cCirc
	 * @return cCirc
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getCCirc() {
		return cCirc;
	}

	public void setCCirc(BigDecimal cCirc) {
		this.cCirc = cCirc;
	}

	public ChestCirc timestamp(MeasurementDate timestamp) {
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
		ChestCirc chestCirc = (ChestCirc) o;
		return Objects.equals(this.dataAvailability, chestCirc.dataAvailability)
				&& Objects.equals(this.cCirc, chestCirc.cCirc) && Objects.equals(this.timestamp, chestCirc.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, cCirc, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ChestCirc {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    cCirc: ").append(toIndentedString(cCirc)).append("\n");
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
