package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Maximum distance between medial and lateral surfaces of the foot perpendicular to the longitudinal axis of the foot
 */
@ApiModel(description = "Maximum distance between medial and lateral surfaces of the foot perpendicular to the longitudinal axis of the foot")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class FootBreadth {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("f_bre")
	private BigDecimal fBre = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public FootBreadth dataAvailability(DataAvailability dataAvailability) {
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

	public FootBreadth fBre(BigDecimal fBre) {
		this.fBre = fBre;
		return this;
	}

	/**
	 * Get fBre
	 * @return fBre
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getFBre() {
		return fBre;
	}

	public void setFBre(BigDecimal fBre) {
		this.fBre = fBre;
	}

	public FootBreadth timestamp(MeasurementDate timestamp) {
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
		FootBreadth footBreadth = (FootBreadth) o;
		return Objects.equals(this.dataAvailability, footBreadth.dataAvailability)
				&& Objects.equals(this.fBre, footBreadth.fBre) && Objects.equals(this.timestamp, footBreadth.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, fBre, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FootBreadth {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    fBre: ").append(toIndentedString(fBre)).append("\n");
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
