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
 * Hair colour described in scales (CIELAB, Fischer-Saller)
 */
@ApiModel(description = "Hair colour described in scales (CIELAB, Fischer-Saller)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class HairColor {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	@JsonProperty("hcPalet")
	private Integer hcPalet = null;

	/**
	 * Gets or Sets fisherSaller
	 */
	public enum FisherSallerEnum {
		A("A"),

		B("B"),

		C("C"),

		D("D"),

		E("E"),

		F("F"),

		G("G"),

		H("H"),

		I("I"),

		J("J"),

		K("K"),

		L("L"),

		M("M"),

		N("N"),

		O("O"),

		P("P"),

		Q("Q"),

		R("R"),

		S("S"),

		T("T"),

		U("U"),

		V("V"),

		W("W"),

		X("X"),

		Y("Y"),

		_1("1"),

		_2("2"),

		_3("3"),

		_4("4"),

		_5("5"),

		_6("6");

		private String value;

		FisherSallerEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static FisherSallerEnum fromValue(String text) {
			for (FisherSallerEnum b : FisherSallerEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("Fisher-Saller")
	private FisherSallerEnum fisherSaller = null;

	@JsonProperty("CIELAB")
	private CIELAB CIELAB = null;

	@JsonProperty("Descriptive")
	private String descriptive = null;

	@JsonProperty("timestamp")
	private MeasurementDate timestamp = null;

	public HairColor dataAvailability(DataAvailability dataAvailability) {
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

	public HairColor hcPalet(Integer hcPalet) {
		this.hcPalet = hcPalet;
		return this;
	}

	/**
	 * Get hcPalet
	 * @return hcPalet
	**/
	@ApiModelProperty(value = "")

	public Integer getHcPalet() {
		return hcPalet;
	}

	public void setHcPalet(Integer hcPalet) {
		this.hcPalet = hcPalet;
	}

	public HairColor fisherSaller(FisherSallerEnum fisherSaller) {
		this.fisherSaller = fisherSaller;
		return this;
	}

	/**
	 * Get fisherSaller
	 * @return fisherSaller
	**/
	@ApiModelProperty(value = "")

	public FisherSallerEnum getFisherSaller() {
		return fisherSaller;
	}

	public void setFisherSaller(FisherSallerEnum fisherSaller) {
		this.fisherSaller = fisherSaller;
	}

	public HairColor CIELAB(CIELAB CIELAB) {
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

	public HairColor descriptive(String descriptive) {
		this.descriptive = descriptive;
		return this;
	}

	/**
	 * declarative hair colour
	 * @return descriptive
	**/
	@ApiModelProperty(value = "declarative hair colour")

	public String getDescriptive() {
		return descriptive;
	}

	public void setDescriptive(String descriptive) {
		this.descriptive = descriptive;
	}

	public HairColor timestamp(MeasurementDate timestamp) {
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
		HairColor hairColor = (HairColor) o;
		return Objects.equals(this.dataAvailability, hairColor.dataAvailability)
				&& Objects.equals(this.hcPalet, hairColor.hcPalet)
				&& Objects.equals(this.fisherSaller, hairColor.fisherSaller)
				&& Objects.equals(this.CIELAB, hairColor.CIELAB)
				&& Objects.equals(this.descriptive, hairColor.descriptive)
				&& Objects.equals(this.timestamp, hairColor.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, hcPalet, fisherSaller, CIELAB, descriptive, timestamp);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class HairColor {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    hcPalet: ").append(toIndentedString(hcPalet)).append("\n");
		sb.append("    fisherSaller: ").append(toIndentedString(fisherSaller)).append("\n");
		sb.append("    CIELAB: ").append(toIndentedString(CIELAB)).append("\n");
		sb.append("    descriptive: ").append(toIndentedString(descriptive)).append("\n");
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
