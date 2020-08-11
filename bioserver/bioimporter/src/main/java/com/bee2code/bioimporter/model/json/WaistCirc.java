package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Horizontal circumference of the trunk at level midway between the lowest ribs and the upper iliac crest
 */
@ApiModel(description = "Horizontal circumference of the trunk at level midway between the lowest ribs and the upper iliac crest")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class WaistCirc {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("w_circ")
	private BigDecimal wCirc = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public WaistCirc dataAvailability(DataAvailability dataAvailability) {
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

	public WaistCirc wCirc(BigDecimal wCirc) {
		this.wCirc = wCirc;
		return this;
	}

	/**
	 * Get wCirc
	 * @return wCirc
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getWCirc() {
		return wCirc;
	}

	public void setWCirc(BigDecimal wCirc) {
		this.wCirc = wCirc;
	}

	public WaistCirc timestamp(MeasurementDate timestamp) {
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
		WaistCirc waistCirc = (WaistCirc) o;
		return Objects.equals(this.dataAvailability, waistCirc.dataAvailability)
				&& Objects.equals(this.wCirc, waistCirc.wCirc) && Objects.equals(this.timestamp, waistCirc.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, wCirc, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class WaistCirc {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    wCirc: ").append(toIndentedString(wCirc)).append("\n");
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
