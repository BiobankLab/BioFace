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
 * Skin tone described with the use of scales (Michalski and von Lunchen, CIELAB scales)
 */
@ApiModel(description = "Skin tone described with the use of scales (Michalski and von Lunchen, CIELAB scales)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SkinTone {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("vonLuschan")
	private Integer vonLuschan = null;

	@JsonProperty("michalski")
	private Integer michalski = null;

	@JsonProperty("CIELAB")
	private CIELAB CIELAB = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public SkinTone dataAvailability(DataAvailability dataAvailability) {
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

	public SkinTone vonLuschan(Integer vonLuschan) {
		this.vonLuschan = vonLuschan;
		return this;
	}

	/**
	 * von Luschan scale
	 * minimum: 1
	 * maximum: 32
	 * @return vonLuschan
	**/
	@ApiModelProperty(value = "von Luschan scale")

	@Min(1)
	@Max(32)
	public Integer getVonLuschan() {
		return vonLuschan;
	}

	public void setVonLuschan(Integer vonLuschan) {
		this.vonLuschan = vonLuschan;
	}

	public SkinTone michalski(Integer michalski) {
		this.michalski = michalski;
		return this;
	}

	/**
	 * Michalski scale
	 * @return michalski
	**/
	@ApiModelProperty(value = "Michalski scale")

	public Integer getMichalski() {
		return michalski;
	}

	public void setMichalski(Integer michalski) {
		this.michalski = michalski;
	}

	public SkinTone CIELAB(CIELAB CIELAB) {
		this.CIELAB = CIELAB;
		return this;
	}

	/**
	 * Get CIELAB
	 * @return CIELAB
	**/
	@ApiModelProperty(value = "")

	@Valid

	public CIELAB getCIELAB() {
		return CIELAB;
	}

	public void setCIELAB(CIELAB CIELAB) {
		this.CIELAB = CIELAB;
	}

	public SkinTone timestamp(MeasurementDate timestamp) {
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
		SkinTone skinTone = (SkinTone) o;
		return Objects.equals(this.dataAvailability, skinTone.dataAvailability)
				&& Objects.equals(this.vonLuschan, skinTone.vonLuschan)
				&& Objects.equals(this.michalski, skinTone.michalski) && Objects.equals(this.CIELAB, skinTone.CIELAB)
				&& Objects.equals(this.timestamp, skinTone.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, vonLuschan, michalski, CIELAB, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SkinTone {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    vonLuschan: ").append(toIndentedString(vonLuschan)).append("\n");
		sb.append("    michalski: ").append(toIndentedString(michalski)).append("\n");
		sb.append("    CIELAB: ").append(toIndentedString(CIELAB)).append("\n");
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
