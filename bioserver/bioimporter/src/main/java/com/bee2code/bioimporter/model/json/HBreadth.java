package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Maximum breadth of head above the level of the ears, measured perpendicular to the midsagittal plane (eu-eu) (&#39;ISO 7250-1:2017&#39;)
 */
@ApiModel(description = "Maximum breadth of head above the level of the ears, measured perpendicular to the midsagittal plane (eu-eu) ('ISO 7250-1:2017')")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class HBreadth {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("h_bre")
	private BigDecimal hBre = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public HBreadth dataAvailability(DataAvailability dataAvailability) {
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

	public HBreadth hBre(BigDecimal hBre) {
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

	public HBreadth timestamp(MeasurementDate timestamp) {
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
		HBreadth hBreadth = (HBreadth) o;
		return Objects.equals(this.dataAvailability, hBreadth.dataAvailability)
				&& Objects.equals(this.hBre, hBreadth.hBre) && Objects.equals(this.timestamp, hBreadth.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, hBre, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class HBreadth {\n");

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
