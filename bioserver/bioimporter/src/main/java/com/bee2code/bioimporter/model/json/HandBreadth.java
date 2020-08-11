package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Projected distance between radial and ulnar metacarapls at the level of the metacarpal heads from the second to the fifth metacarpal, measured peripendicular to the long axis of the middle finger
 */
@ApiModel(description = "Projected distance between radial and ulnar metacarapls at the level of the metacarpal heads from the second to the fifth metacarpal, measured peripendicular to the long axis of the middle finger")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class HandBreadth {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("h_bre")
	private BigDecimal hBre = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public HandBreadth dataAvailability(DataAvailability dataAvailability) {
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

	public HandBreadth hBre(BigDecimal hBre) {
		this.hBre = hBre;
		return this;
	}

	/**
	 * Get hBre
	 * @return hBre
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getHBre() {
		return hBre;
	}

	public void setHBre(BigDecimal hBre) {
		this.hBre = hBre;
	}

	public HandBreadth timestamp(MeasurementDate timestamp) {
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
		HandBreadth handBreadth = (HandBreadth) o;
		return Objects.equals(this.dataAvailability, handBreadth.dataAvailability)
				&& Objects.equals(this.hBre, handBreadth.hBre) && Objects.equals(this.timestamp, handBreadth.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, hBre, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class HandBreadth {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    hBre: ").append(toIndentedString(hBre)).append("\n");
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
