package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * medical procedure (icd-9, snomed, noCode descriptive name of procedure)
 */
@ApiModel(description = "medical procedure (icd-9, snomed, noCode descriptive name of procedure)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class MedicalProcedure {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("snomed")
	private Integer snomed = null;

	@JsonProperty("icd-9")
	private String icd9 = null;

	@JsonProperty("noCode")
	private String noCode = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public MedicalProcedure dataAvailability(DataAvailability dataAvailability) {
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

	public MedicalProcedure snomed(Integer snomed) {
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

	public MedicalProcedure icd9(String icd9) {
		this.icd9 = icd9;
		return this;
	}

	/**
	 * Get icd9
	 * @return icd9
	**/
	@ApiModelProperty(value = "")

	@Pattern(regexp = "([V\\d]\\d{2}(\\.?\\d{0,2})?|E\\d{3}(\\.?\\d)?|\\d{2}(\\.?\\d{0,2})?)")
	public String getIcd9() {
		return icd9;
	}

	public void setIcd9(String icd9) {
		this.icd9 = icd9;
	}

	public MedicalProcedure noCode(String noCode) {
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

	public MedicalProcedure timestamp(MeasurementDate timestamp) {
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
		MedicalProcedure medicalProcedure = (MedicalProcedure) o;
		return Objects.equals(this.dataAvailability, medicalProcedure.dataAvailability)
				&& Objects.equals(this.snomed, medicalProcedure.snomed)
				&& Objects.equals(this.icd9, medicalProcedure.icd9)
				&& Objects.equals(this.noCode, medicalProcedure.noCode)
				&& Objects.equals(this.timestamp, medicalProcedure.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, snomed, icd9, noCode, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MedicalProcedure {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    snomed: ").append(toIndentedString(snomed)).append("\n");
		sb.append("    icd9: ").append(toIndentedString(icd9)).append("\n");
		sb.append("    noCode: ").append(toIndentedString(noCode)).append("\n");
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
