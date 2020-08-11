package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Body mass index
 */
@ApiModel(description = "Body mass index")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class BMI {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("BMI")
	private BigDecimal BMI = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public BMI dataAvailability(DataAvailability dataAvailability) {
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

	public BMI BMI(BigDecimal BMI) {
		this.BMI = BMI;
		return this;
	}

	/**
	 * Get BMI
	 * @return BMI
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getBMI() {
		return BMI;
	}

	public void setBMI(BigDecimal BMI) {
		this.BMI = BMI;
	}

	public BMI timestamp(MeasurementDate timestamp) {
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
		BMI BMI = (BMI) o;
		return Objects.equals(this.dataAvailability, BMI.dataAvailability) && Objects.equals(this.BMI, BMI.BMI)
				&& Objects.equals(this.timestamp, BMI.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, BMI, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BMI {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    BMI: ").append(toIndentedString(BMI)).append("\n");
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
