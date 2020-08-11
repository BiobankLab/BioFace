package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * SampleInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleInfo {
	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("sampleId")
	private String sampleId = null;

	@JsonProperty("parentSampleId")
	private String parentSampleId = null;

	/**
	 * Gets or Sets accesion
	 */
	public enum AccesionEnum {
		ACCESSIBLE("accessible"),

		INACCESSIBLE("inaccessible"),

		LIMITED_ACCESS("limited access");

		private String value;

		AccesionEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AccesionEnum fromValue(String text) {
			for (AccesionEnum b : AccesionEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("accesion")
	private AccesionEnum accesion = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	@JsonProperty("collection")
	private String collection = null;

	@JsonProperty("biobank")
	private String biobank = null;

	@JsonProperty("owner")
	private String owner = null;

	@JsonProperty("comments")
	private String comments = null;

	public SampleInfo id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * @return id
	**/
	@ApiModelProperty(value = "")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SampleInfo sampleId(String sampleId) {
		this.sampleId = sampleId;
		return this;
	}

	/**
	 * Get sampleId
	 * @return sampleId
	**/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public SampleInfo parentSampleId(String parentSampleId) {
		this.parentSampleId = parentSampleId;
		return this;
	}

	/**
	 * Get parentSampleId
	 * @return parentSampleId
	**/
	@ApiModelProperty(value = "")

	public String getParentSampleId() {
		return parentSampleId;
	}

	public void setParentSampleId(String parentSampleId) {
		this.parentSampleId = parentSampleId;
	}

	public SampleInfo accesion(AccesionEnum accesion) {
		this.accesion = accesion;
		return this;
	}

	/**
	 * Get accesion
	 * @return accesion
	**/
	@ApiModelProperty(value = "")

	public AccesionEnum getAccesion() {
		return accesion;
	}

	public void setAccesion(AccesionEnum accesion) {
		this.accesion = accesion;
	}

	public SampleInfo timestamp(MeasurementDate timestamp) {
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

	public SampleInfo collection(String collection) {
		this.collection = collection;
		return this;
	}

	/**
	 * BioBank collection
	 * @return collection
	**/
	@ApiModelProperty(value = "BioBank collection")

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public SampleInfo biobank(String biobank) {
		this.biobank = biobank;
		return this;
	}

	/**
	 * Sample banking insttution
	 * @return biobank
	**/
	@ApiModelProperty(value = "Sample banking insttution")

	public String getBiobank() {
		return biobank;
	}

	public void setBiobank(String biobank) {
		this.biobank = biobank;
	}

	public SampleInfo owner(String owner) {
		this.owner = owner;
		return this;
	}

	/**
	 * Sample owner
	 * @return owner
	**/
	@ApiModelProperty(value = "Sample owner")

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public SampleInfo comments(String comments) {
		this.comments = comments;
		return this;
	}

	/**
	 * Additional information about sample (warnings, problems, QM, QC)
	 * @return comments
	**/
	@ApiModelProperty(value = "Additional information about sample (warnings, problems, QM, QC)")

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SampleInfo sampleInfo = (SampleInfo) o;
		return Objects.equals(this.id, sampleInfo.id) && Objects.equals(this.sampleId, sampleInfo.sampleId)
				&& Objects.equals(this.parentSampleId, sampleInfo.parentSampleId)
				&& Objects.equals(this.accesion, sampleInfo.accesion)
				&& Objects.equals(this.timestamp, sampleInfo.timestamp)
				&& Objects.equals(this.collection, sampleInfo.collection)
				&& Objects.equals(this.biobank, sampleInfo.biobank) && Objects.equals(this.owner, sampleInfo.owner)
				&& Objects.equals(this.comments, sampleInfo.comments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, sampleId, parentSampleId, accesion, timestamp, collection, biobank, owner, comments);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    sampleId: ").append(toIndentedString(sampleId)).append("\n");
		sb.append("    parentSampleId: ").append(toIndentedString(parentSampleId)).append("\n");
		sb.append("    accesion: ").append(toIndentedString(accesion)).append("\n");
		sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
		sb.append("    collection: ").append(toIndentedString(collection)).append("\n");
		sb.append("    biobank: ").append(toIndentedString(biobank)).append("\n");
		sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
		sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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
