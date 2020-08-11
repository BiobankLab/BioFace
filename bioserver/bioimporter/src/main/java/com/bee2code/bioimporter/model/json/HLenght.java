package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Distance along a straight line between glabella and opisthocranion (g-op) (&#39;ISO 7250-1:2017&#39;)
 */
@ApiModel(description = "Distance along a straight line between glabella and opisthocranion (g-op) ('ISO 7250-1:2017')")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class HLenght {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("h_len")
	private BigDecimal hLen = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public HLenght dataAvailability(DataAvailability dataAvailability) {
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

	public HLenght hLen(BigDecimal hLen) {
		this.hLen = hLen;
		return this;
	}

	/**
	 * Get hLen
	 * @return hLen
	**/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getHLen() {
		return hLen;
	}

	public void setHLen(BigDecimal hLen) {
		this.hLen = hLen;
	}

	public HLenght timestamp(MeasurementDate timestamp) {
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
		HLenght hLenght = (HLenght) o;
		return Objects.equals(this.dataAvailability, hLenght.dataAvailability)
				&& Objects.equals(this.hLen, hLenght.hLen) && Objects.equals(this.timestamp, hLenght.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, hLen, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class HLenght {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    hLen: ").append(toIndentedString(hLen)).append("\n");
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
