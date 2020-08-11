package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Corpulence index (Rohrer&#39;s index)
 */
@ApiModel(description = "Corpulence index (Rohrer's index)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class CI {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("CI")
	private BigDecimal CI = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public CI dataAvailability(DataAvailability dataAvailability) {
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

	public CI CI(BigDecimal CI) {
		this.CI = CI;
		return this;
	}

	/**
	 * Get CI
	 * @return CI
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getCI() {
		return CI;
	}

	public void setCI(BigDecimal CI) {
		this.CI = CI;
	}

	public CI timestamp(MeasurementDate timestamp) {
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
		CI CI = (CI) o;
		return Objects.equals(this.dataAvailability, CI.dataAvailability) && Objects.equals(this.CI, CI.CI)
				&& Objects.equals(this.timestamp, CI.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, CI, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CI {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    CI: ").append(toIndentedString(CI)).append("\n");
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
