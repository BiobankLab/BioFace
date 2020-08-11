package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Waist - hip ratio
 */
@ApiModel(description = "Waist - hip ratio")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class WHR {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("WHR")
	private BigDecimal WHR = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public WHR dataAvailability(DataAvailability dataAvailability) {
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

	public WHR WHR(BigDecimal WHR) {
		this.WHR = WHR;
		return this;
	}

	/**
	 * Get WHR
	 * @return WHR
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getWHR() {
		return WHR;
	}

	public void setWHR(BigDecimal WHR) {
		this.WHR = WHR;
	}

	public WHR timestamp(MeasurementDate timestamp) {
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
		WHR WHR = (WHR) o;
		return Objects.equals(this.dataAvailability, WHR.dataAvailability) && Objects.equals(this.WHR, WHR.WHR)
				&& Objects.equals(this.timestamp, WHR.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, WHR, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class WHR {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    WHR: ").append(toIndentedString(WHR)).append("\n");
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
