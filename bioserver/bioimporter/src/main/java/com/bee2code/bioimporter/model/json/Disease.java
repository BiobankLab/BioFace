package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Disease
 */
@ApiModel(description = "Disease")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class Disease {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("icd-10")
	private String icd10 = null;

	@JsonProperty("noCode")
	private String noCode = null;

	@JsonProperty("snomed")
	private Integer snomed = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public Disease dataAvailability(DataAvailability dataAvailability) {
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

	public Disease icd10(String icd10) {
		this.icd10 = icd10;
		return this;
	}

	/**
	 * Get icd10
	 * @return icd10
	**/
	@ApiModelProperty(value = "")

	@Pattern(regexp = "([A-TV-Z][0-9][A-Z0-9](\\.?[A-Z0-9]{0,4})?)")
	public String getIcd10() {
		return icd10;
	}

	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}

	public Disease noCode(String noCode) {
		this.noCode = noCode;
		return this;
	}

	/**
	 * Get noCode
	 * @return noCode
	**/
	@ApiModelProperty(value = "")

	public String getNoCode() {
		return noCode;
	}

	public void setNoCode(String noCode) {
		this.noCode = noCode;
	}

	public Disease snomed(Integer snomed) {
		this.snomed = snomed;
		return this;
	}

	/**
	 * Get snomed
	 * @return snomed
	**/
	@ApiModelProperty(value = "")

	public Integer getSnomed() {
		return snomed;
	}

	public void setSnomed(Integer snomed) {
		this.snomed = snomed;
	}

	public Disease timestamp(MeasurementDate timestamp) {
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
		Disease disease = (Disease) o;
		return Objects.equals(this.dataAvailability, disease.dataAvailability)
				&& Objects.equals(this.icd10, disease.icd10) && Objects.equals(this.noCode, disease.noCode)
				&& Objects.equals(this.snomed, disease.snomed) && Objects.equals(this.timestamp, disease.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, icd10, noCode, snomed, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Disease {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    icd10: ").append(toIndentedString(icd10)).append("\n");
		sb.append("    noCode: ").append(toIndentedString(noCode)).append("\n");
		sb.append("    snomed: ").append(toIndentedString(snomed)).append("\n");
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
