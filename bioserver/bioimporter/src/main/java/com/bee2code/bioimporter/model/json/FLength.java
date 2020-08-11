package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Morphological face length measured as a distance between nasion and gnathion (n-gn) (&#39;ISO 7250-1:2017&#39;)
 */
@ApiModel(description = "Morphological face length measured as a distance between nasion and gnathion (n-gn) ('ISO 7250-1:2017')")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class FLength {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("f_len")
	private BigDecimal fLen = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public FLength dataAvailability(DataAvailability dataAvailability) {
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

	public FLength fLen(BigDecimal fLen) {
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

	public FLength timestamp(MeasurementDate timestamp) {
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
		FLength fLength = (FLength) o;
		return Objects.equals(this.dataAvailability, fLength.dataAvailability)
				&& Objects.equals(this.fLen, fLength.fLen) && Objects.equals(this.timestamp, fLength.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, fLen, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FLength {\n");

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
