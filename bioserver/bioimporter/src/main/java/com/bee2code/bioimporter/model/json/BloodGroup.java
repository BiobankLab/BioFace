package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Blood group based on scales including Rh factor (AB0, Kell, Levis scales)
 */
@ApiModel(description = "Blood group based on scales including Rh factor (AB0, Kell, Levis scales)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class BloodGroup {
	@JsonProperty("data_availability")
	private DataAvailability dataAvailability = null;

	/**
	 * Gets or Sets ABO
	 */

	@JsonProperty("ABO")
	private ABOEnum ABO = null;

	/**
	 * Gets or Sets rh
	 */

	@JsonProperty("Rh")
	private RhEnum rh = null;

	/**
	 * Gets or Sets lewis
	 */

	@JsonProperty("Lewis")
	private LewisEnum lewis = null;

	/**
	 * Gets or Sets kell
	 */

	@JsonProperty("Kell")
	private KellEnum kell = null;

	@JsonProperty("Other")
	private String other = null;

	public BloodGroup dataAvailability(DataAvailability dataAvailability) {
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

	public BloodGroup ABO(ABOEnum ABO) {
		this.ABO = ABO;
		return this;
	}

	/**
	 * Get ABO
	 * @return ABO
	**/
	@ApiModelProperty(value = "")

	public ABOEnum getABO() {
		return ABO;
	}

	public void setABO(ABOEnum ABO) {
		this.ABO = ABO;
	}

	public BloodGroup rh(RhEnum rh) {
		this.rh = rh;
		return this;
	}

	/**
	 * Get rh
	 * @return rh
	**/
	@ApiModelProperty(value = "")

	public RhEnum getRh() {
		return rh;
	}

	public void setRh(RhEnum rh) {
		this.rh = rh;
	}

	public BloodGroup lewis(LewisEnum lewis) {
		this.lewis = lewis;
		return this;
	}

	/**
	 * Get lewis
	 * @return lewis
	**/
	@ApiModelProperty(value = "")

	public LewisEnum getLewis() {
		return lewis;
	}

	public void setLewis(LewisEnum lewis) {
		this.lewis = lewis;
	}

	public BloodGroup kell(KellEnum kell) {
		this.kell = kell;
		return this;
	}

	/**
	 * Get kell
	 * @return kell
	**/
	@ApiModelProperty(value = "")

	public KellEnum getKell() {
		return kell;
	}

	public void setKell(KellEnum kell) {
		this.kell = kell;
	}

	public BloodGroup other(String other) {
		this.other = other;
		return this;
	}

	/**
	 * Get other
	 * @return other
	**/
	@ApiModelProperty(value = "")

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BloodGroup bloodGroup = (BloodGroup) o;
		return Objects.equals(this.dataAvailability, bloodGroup.dataAvailability)
				&& Objects.equals(this.ABO, bloodGroup.ABO) && Objects.equals(this.rh, bloodGroup.rh)
				&& Objects.equals(this.lewis, bloodGroup.lewis) && Objects.equals(this.kell, bloodGroup.kell)
				&& Objects.equals(this.other, bloodGroup.other);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAvailability, ABO, rh, lewis, kell, other);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BloodGroup {\n");

		sb.append("    dataAvailability: ").append(toIndentedString(dataAvailability)).append("\n");
		sb.append("    ABO: ").append(toIndentedString(ABO)).append("\n");
		sb.append("    rh: ").append(toIndentedString(rh)).append("\n");
		sb.append("    lewis: ").append(toIndentedString(lewis)).append("\n");
		sb.append("    kell: ").append(toIndentedString(kell)).append("\n");
		sb.append("    other: ").append(toIndentedString(other)).append("\n");
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
