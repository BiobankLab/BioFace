package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Height of the symphysion (B-sy)
 */
@ApiModel(description = "Height of the symphysion (B-sy)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class LowerLimbLen2 {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("lll2")
	private BigDecimal lll2 = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public LowerLimbLen2 dataAvailability(DataAvailability dataAvailability) {
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

	public LowerLimbLen2 lll2(BigDecimal lll2) {
		this.lll2 = lll2;
		return this;
	}

	/**
	 * Get lll2
	 * @return lll2
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getLll2() {
		return lll2;
	}

	public void setLll2(BigDecimal lll2) {
		this.lll2 = lll2;
	}

	public LowerLimbLen2 timestamp(MeasurementDate timestamp) {
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
		LowerLimbLen2 lowerLimbLen2 = (LowerLimbLen2) o;
		return Objects.equals(this.dataAvailability, lowerLimbLen2.dataAvailability)
				&& Objects.equals(this.lll2, lowerLimbLen2.lll2)
				&& Objects.equals(this.timestamp, lowerLimbLen2.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, lll2, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LowerLimbLen2 {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    lll2: ").append(toIndentedString(lll2)).append("\n");
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
