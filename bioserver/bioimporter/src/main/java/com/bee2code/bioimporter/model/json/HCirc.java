package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Maximum, approximately horizontal, circumference of head measured above the galbella and crossing the rearmost point of skull (&#39;ISO 7250-1:2017&#39;)
 */
@ApiModel(description = "Maximum, approximately horizontal, circumference of head measured above the galbella and crossing the rearmost point of skull ('ISO 7250-1:2017')")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class HCirc {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("h_circ")
	private BigDecimal hCirc = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public HCirc dataAvailability(DataAvailability dataAvailability) {
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

	public HCirc hCirc(BigDecimal hCirc) {
		this.hCirc = hCirc;
		return this;
	}

	/**
	 * Get hCirc
	 * @return hCirc
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getHCirc() {
		return hCirc;
	}

	public void setHCirc(BigDecimal hCirc) {
		this.hCirc = hCirc;
	}

	public HCirc timestamp(MeasurementDate timestamp) {
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
		HCirc hCirc = (HCirc) o;
		return Objects.equals(this.dataAvailability, hCirc.dataAvailability) && Objects.equals(this.hCirc, hCirc.hCirc)
				&& Objects.equals(this.timestamp, hCirc.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, hCirc, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class HCirc {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    hCirc: ").append(toIndentedString(hCirc)).append("\n");
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
