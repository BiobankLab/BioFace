package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * sample material
 */
@ApiModel(description = "sample material")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class Sample {
	/**
	 * Gets or Sets materialType
	 */
	public enum MaterialTypeEnum {
	NUCLEIC_ACIDS("Nucleic Acids"),

	WHOLE_BLOOD("Whole blood"),

	BUFFY_COAT("Buffy coat"),

	PLASMA("Plasma"),

	SERUM("Serum"),

	TISSUE("Tissue"),

	FFPE("FFPE"),

	URINE("Urine"),

	SALIVA("Saliva"),

	STOOL("Stool"),

	OTHER("Other");

		private String value;

		MaterialTypeEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static MaterialTypeEnum fromValue(String text) {
			for (MaterialTypeEnum b : MaterialTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("materialType")
	private MaterialTypeEnum materialType = null;

	/**
	 * values in Celsius degrees scale
	 */
	public enum StorageTemperatureEnum {
		ROOM_TEMPERATURE("Room temperature"),

		_2_TO_10("+2 to +10"),

		_18_TO_35("-18 to -35"),

		_60_TO_85("-60 to -85"),

		_150_TO_196("-150 to -196"),

		OTHER("other");

		private String value;

		StorageTemperatureEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static StorageTemperatureEnum fromValue(String text) {
			for (StorageTemperatureEnum b : StorageTemperatureEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("storageTemperature")
	private StorageTemperatureEnum storageTemperature = null;

	@JsonProperty("SampleMaterial")
	private SampleMaterial sampleMaterial = null;

	@JsonProperty("SampleInfo")
	private SampleInfo sampleInfo = null;

	@JsonProperty("SampleQMS")
	private SampleQMS sampleQMS = null;

	public Sample materialType(MaterialTypeEnum materialType) {
		this.materialType = materialType;
		return this;
	}

	/**
	 * Get materialType
	 * @return materialType
	**/
	@ApiModelProperty(value = "")

	public MaterialTypeEnum getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialTypeEnum materialType) {
		this.materialType = materialType;
	}

	public Sample storageTemperature(StorageTemperatureEnum storageTemperature) {
		this.storageTemperature = storageTemperature;
		return this;
	}

	/**
	 * values in Celsius degrees scale
	 * @return storageTemperature
	**/
	@ApiModelProperty(value = "values in Celsius degrees scale")

	public StorageTemperatureEnum getStorageTemperature() {
		return storageTemperature;
	}

	public void setStorageTemperature(StorageTemperatureEnum storageTemperature) {
		this.storageTemperature = storageTemperature;
	}

	public Sample sampleMaterial(SampleMaterial sampleMaterial) {
		this.sampleMaterial = sampleMaterial;
		return this;
	}

	/**
	 * Get sampleMaterial
	 * @return sampleMaterial
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterial getSampleMaterial() {
		return sampleMaterial;
	}

	public void setSampleMaterial(SampleMaterial sampleMaterial) {
		this.sampleMaterial = sampleMaterial;
	}

	public Sample sampleInfo(SampleInfo sampleInfo) {
		this.sampleInfo = sampleInfo;
		return this;
	}

	/**
	 * Get sampleInfo
	 * @return sampleInfo
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleInfo getSampleInfo() {
		return sampleInfo;
	}

	public void setSampleInfo(SampleInfo sampleInfo) {
		this.sampleInfo = sampleInfo;
	}

	public Sample sampleQMS(SampleQMS sampleQMS) {
		this.sampleQMS = sampleQMS;
		return this;
	}

	/**
	 * Get sampleQMS
	 * @return sampleQMS
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleQMS getSampleQMS() {
		return sampleQMS;
	}

	public void setSampleQMS(SampleQMS sampleQMS) {
		this.sampleQMS = sampleQMS;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Sample sample = (Sample) o;
		return Objects.equals(this.materialType, sample.materialType)
				&& Objects.equals(this.storageTemperature, sample.storageTemperature)
				&& Objects.equals(this.sampleMaterial, sample.sampleMaterial)
				&& Objects.equals(this.sampleInfo, sample.sampleInfo)
				&& Objects.equals(this.sampleQMS, sample.sampleQMS);
	}

	@Override
	public int hashCode() {
		return Objects.hash(materialType, storageTemperature, sampleMaterial, sampleInfo, sampleQMS);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Sample {\n");

		sb.append("    materialType: ").append(toIndentedString(materialType)).append("\n");
		sb.append("    storageTemperature: ").append(toIndentedString(storageTemperature)).append("\n");
		sb.append("    sampleMaterial: ").append(toIndentedString(sampleMaterial)).append("\n");
		sb.append("    sampleInfo: ").append(toIndentedString(sampleInfo)).append("\n");
		sb.append("    sampleQMS: ").append(toIndentedString(sampleQMS)).append("\n");
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
