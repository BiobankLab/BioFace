package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Maximum distance from rear of the heel to tip of the longest toe, measured parallel to the longitudinal axis of the foot
 */
@ApiModel(description = "Maximum distance from rear of the heel to tip of the longest toe, measured parallel to the longitudinal axis of the foot")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class FootLength {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("f_len")
	private BigDecimal fLen = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public FootLength dataAvailability(DataAvailability dataAvailability) {
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

	public FootLength fLen(BigDecimal fLen) {
		this.fLen = fLen;
		return this;
	}

	/**
	 * Get fLen
	 * @return fLen
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getFLen() {
		return fLen;
	}

	public void setFLen(BigDecimal fLen) {
		this.fLen = fLen;
	}

	public FootLength timestamp(MeasurementDate timestamp) {
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
		FootLength footLength = (FootLength) o;
		return Objects.equals(this.dataAvailability, footLength.dataAvailability)
				&& Objects.equals(this.fLen, footLength.fLen) && Objects.equals(this.timestamp, footLength.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, fLen, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FootLength {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    fLen: ").append(toIndentedString(fLen)).append("\n");
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
