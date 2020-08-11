package com.bee2code.bioimporter.model.json;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * measurement date
 */
@ApiModel(description = "measurement date")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class MeasurementDate {
	@JsonProperty("date")
	private OffsetDateTime date = null;

	@JsonProperty("dateCorrectess")
	private String dateCorrectess = null;

	public MeasurementDate date(OffsetDateTime date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 * @return date
	**/
	@ApiModelProperty(value = "")

	@Valid

	public OffsetDateTime getDate() {
		return date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
	}

	public MeasurementDate dateCorrectess(String dateCorrectess) {
		this.dateCorrectess = dateCorrectess;
		return this;
	}

	/**
	 * Get dateCorrectess
	 * @return dateCorrectess
	**/
	@ApiModelProperty(value = "")

	public String getDateCorrectess() {
		return dateCorrectess;
	}

	public void setDateCorrectess(String dateCorrectess) {
		this.dateCorrectess = dateCorrectess;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MeasurementDate measurementDate = (MeasurementDate) o;
		return Objects.equals(this.date, measurementDate.date)
				&& Objects.equals(this.dateCorrectess, measurementDate.dateCorrectess);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, dateCorrectess);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MeasurementDate {\n");

		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    dateCorrectess: ").append(toIndentedString(dateCorrectess)).append("\n");
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
