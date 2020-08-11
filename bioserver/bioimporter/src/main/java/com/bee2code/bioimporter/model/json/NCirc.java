package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Circumference of neck at the point just below the bulge at the thyroid cartilage, perpendicular to the long axis of the neck (&#39;ISO 7250-1:2017&#39;)
 */
@ApiModel(description = "Circumference of neck at the point just below the bulge at the thyroid cartilage, perpendicular to the long axis of the neck ('ISO 7250-1:2017')")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class NCirc {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("n_circ")
	private BigDecimal nCirc = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public NCirc dataAvailability(DataAvailability dataAvailability) {
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

	public NCirc nCirc(BigDecimal nCirc) {
		this.nCirc = nCirc;
		return this;
	}

	/**
	 * Get nCirc
	 * @return nCirc
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getNCirc() {
		return nCirc;
	}

	public void setNCirc(BigDecimal nCirc) {
		this.nCirc = nCirc;
	}

	public NCirc timestamp(MeasurementDate timestamp) {
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
		NCirc nCirc = (NCirc) o;
		return Objects.equals(this.dataAvailability, nCirc.dataAvailability) && Objects.equals(this.nCirc, nCirc.nCirc)
				&& Objects.equals(this.timestamp, nCirc.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, nCirc, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class NCirc {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    nCirc: ").append(toIndentedString(nCirc)).append("\n");
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
