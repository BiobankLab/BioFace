package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Height of the point trochanterion (B-tro)
 */
@ApiModel(description = "Height of the point trochanterion (B-tro)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class LowerLimbLen1 {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("lll1")
	private BigDecimal lll1 = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public LowerLimbLen1 dataAvailability(DataAvailability dataAvailability) {
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

	public LowerLimbLen1 lll1(BigDecimal lll1) {
		this.lll1 = lll1;
		return this;
	}

	/**
	 * Get lll1
	 * @return lll1
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getLll1() {
		return lll1;
	}

	public void setLll1(BigDecimal lll1) {
		this.lll1 = lll1;
	}

	public LowerLimbLen1 timestamp(MeasurementDate timestamp) {
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
		LowerLimbLen1 lowerLimbLen1 = (LowerLimbLen1) o;
		return Objects.equals(this.dataAvailability, lowerLimbLen1.dataAvailability)
				&& Objects.equals(this.lll1, lowerLimbLen1.lll1)
				&& Objects.equals(this.timestamp, lowerLimbLen1.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, lll1, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LowerLimbLen1 {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    lll1: ").append(toIndentedString(lll1)).append("\n");
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
