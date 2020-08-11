package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Eye colour described in scale (Michalski, Martin-Schultz)
 */
@ApiModel(description = "Eye colour described in scale (Michalski, Martin-Schultz)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class EyeColour {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("Martin-Schultz")
	private Integer martinSchultz = null;

	@JsonProperty("Michalski")
	private Integer michalski = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public EyeColour dataAvailability(DataAvailability dataAvailability) {
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

	public EyeColour martinSchultz(Integer martinSchultz) {
		this.martinSchultz = martinSchultz;
		return this;
	}

	/**
	 * Get martinSchultz
	 * minimum: 1
	 * maximum: 16
	 * @return martinSchultz
	**/
	@ApiModelProperty(value = "")

	@Min(1)
	@Max(16)
	public Integer getMartinSchultz() {
		return martinSchultz;
	}

	public void setMartinSchultz(Integer martinSchultz) {
		this.martinSchultz = martinSchultz;
	}

	public EyeColour michalski(Integer michalski) {
		this.michalski = michalski;
		return this;
	}

	/**
	 * Get michalski
	 * minimum: 1
	 * maximum: 80
	 * @return michalski
	**/
	@ApiModelProperty(value = "")

	@Min(1)
	@Max(80)
	public Integer getMichalski() {
		return michalski;
	}

	public void setMichalski(Integer michalski) {
		this.michalski = michalski;
	}

	public EyeColour timestamp(MeasurementDate timestamp) {
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
		EyeColour eyeColour = (EyeColour) o;
		return Objects.equals(this.dataAvailability, eyeColour.dataAvailability)
				&& Objects.equals(this.martinSchultz, eyeColour.martinSchultz)
				&& Objects.equals(this.michalski, eyeColour.michalski)
				&& Objects.equals(this.timestamp, eyeColour.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, martinSchultz, michalski, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class EyeColour {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    martinSchultz: ").append(toIndentedString(martinSchultz)).append("\n");
		sb.append("    michalski: ").append(toIndentedString(michalski)).append("\n");
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
