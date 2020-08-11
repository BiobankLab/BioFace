package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Declarative total body mass given in kilograms (accuracy to 100 g)
 */
@ApiModel(description = "Declarative total body mass given in kilograms (accuracy to 100 g)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class WeightDeclarative {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("weight")
	private BigDecimal weight = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public WeightDeclarative dataAvailability(DataAvailability dataAvailability) {
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

	public WeightDeclarative weight(BigDecimal weight) {
		this.weight = weight;
		return this;
	}

	/**
	 * Get weight
	 * @return weight
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public WeightDeclarative timestamp(MeasurementDate timestamp) {
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
		WeightDeclarative weightDeclarative = (WeightDeclarative) o;
		return Objects.equals(this.dataAvailability, weightDeclarative.dataAvailability)
				&& Objects.equals(this.weight, weightDeclarative.weight)
				&& Objects.equals(this.timestamp, weightDeclarative.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, weight, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class WeightDeclarative {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
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
