package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * information about the sample
 */
@ApiModel(description = "information about the sample")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleMaterial {
	@JsonProperty("tissue")
	private SampleMaterialTissue tissue = null;

	@JsonProperty("whole_blood")
	private SampleMaterialWholeBlood wholeBlood = null;

	@JsonProperty("serum")
	private SampleMaterialSerum serum = null;

	@JsonProperty("plasma")
	private SampleMaterialPlasma plasma = null;

	@JsonProperty("buffy_coat")
	private SampleMaterialBuffyCoat buffyCoat = null;

	@JsonProperty("FFPE")
	private SampleMaterialFFPE FFPE = null;

	@JsonProperty("Urine")
	private SampleMaterialUrine urine = null;

	@JsonProperty("Saliva")
	private SampleMaterialSaliva saliva = null;

	@JsonProperty("Stool")
	private SampleMaterialStool stool = null;

	@JsonProperty("Nucleic_acid")
	private SampleMaterialNucleicAcid nucleicAcid = null;

	@JsonProperty("Other")
	private String other = null;

	public SampleMaterial tissue(SampleMaterialTissue tissue) {
		this.tissue = tissue;
		return this;
	}

	/**
	 * Get tissue
	 * @return tissue
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialTissue getTissue() {
		return tissue;
	}

	public void setTissue(SampleMaterialTissue tissue) {
		this.tissue = tissue;
	}

	public SampleMaterial wholeBlood(SampleMaterialWholeBlood wholeBlood) {
		this.wholeBlood = wholeBlood;
		return this;
	}

	/**
	 * Get wholeBlood
	 * @return wholeBlood
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialWholeBlood getWholeBlood() {
		return wholeBlood;
	}

	public void setWholeBlood(SampleMaterialWholeBlood wholeBlood) {
		this.wholeBlood = wholeBlood;
	}

	public SampleMaterial serum(SampleMaterialSerum serum) {
		this.serum = serum;
		return this;
	}

	/**
	 * Get serum
	 * @return serum
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialSerum getSerum() {
		return serum;
	}

	public void setSerum(SampleMaterialSerum serum) {
		this.serum = serum;
	}

	public SampleMaterial plasma(SampleMaterialPlasma plasma) {
		this.plasma = plasma;
		return this;
	}

	/**
	 * Get plasma
	 * @return plasma
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialPlasma getPlasma() {
		return plasma;
	}

	public void setPlasma(SampleMaterialPlasma plasma) {
		this.plasma = plasma;
	}

	public SampleMaterial buffyCoat(SampleMaterialBuffyCoat buffyCoat) {
		this.buffyCoat = buffyCoat;
		return this;
	}

	/**
	 * Get buffyCoat
	 * @return buffyCoat
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialBuffyCoat getBuffyCoat() {
		return buffyCoat;
	}

	public void setBuffyCoat(SampleMaterialBuffyCoat buffyCoat) {
		this.buffyCoat = buffyCoat;
	}

	public SampleMaterial FFPE(SampleMaterialFFPE FFPE) {
		this.FFPE = FFPE;
		return this;
	}

	/**
	 * Get FFPE
	 * @return FFPE
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialFFPE getFFPE() {
		return FFPE;
	}

	public void setFFPE(SampleMaterialFFPE FFPE) {
		this.FFPE = FFPE;
	}

	public SampleMaterial urine(SampleMaterialUrine urine) {
		this.urine = urine;
		return this;
	}

	/**
	 * Get urine
	 * @return urine
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialUrine getUrine() {
		return urine;
	}

	public void setUrine(SampleMaterialUrine urine) {
		this.urine = urine;
	}

	public SampleMaterial saliva(SampleMaterialSaliva saliva) {
		this.saliva = saliva;
		return this;
	}

	/**
	 * Get saliva
	 * @return saliva
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialSaliva getSaliva() {
		return saliva;
	}

	public void setSaliva(SampleMaterialSaliva saliva) {
		this.saliva = saliva;
	}

	public SampleMaterial stool(SampleMaterialStool stool) {
		this.stool = stool;
		return this;
	}

	/**
	 * Get stool
	 * @return stool
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialStool getStool() {
		return stool;
	}

	public void setStool(SampleMaterialStool stool) {
		this.stool = stool;
	}

	public SampleMaterial nucleicAcid(SampleMaterialNucleicAcid nucleicAcid) {
		this.nucleicAcid = nucleicAcid;
		return this;
	}

	/**
	 * Get nucleicAcid
	 * @return nucleicAcid
	**/
	@ApiModelProperty(value = "")

	@Valid

	public SampleMaterialNucleicAcid getNucleicAcid() {
		return nucleicAcid;
	}

	public void setNucleicAcid(SampleMaterialNucleicAcid nucleicAcid) {
		this.nucleicAcid = nucleicAcid;
	}

	public SampleMaterial other(String other) {
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
		SampleMaterial sampleMaterial = (SampleMaterial) o;
		return Objects.equals(this.tissue, sampleMaterial.tissue)
				&& Objects.equals(this.wholeBlood, sampleMaterial.wholeBlood)
				&& Objects.equals(this.serum, sampleMaterial.serum)
				&& Objects.equals(this.plasma, sampleMaterial.plasma)
				&& Objects.equals(this.buffyCoat, sampleMaterial.buffyCoat)
				&& Objects.equals(this.FFPE, sampleMaterial.FFPE) && Objects.equals(this.urine, sampleMaterial.urine)
				&& Objects.equals(this.saliva, sampleMaterial.saliva)
				&& Objects.equals(this.stool, sampleMaterial.stool)
				&& Objects.equals(this.nucleicAcid, sampleMaterial.nucleicAcid)
				&& Objects.equals(this.other, sampleMaterial.other);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tissue, wholeBlood, serum, plasma, buffyCoat, FFPE, urine, saliva, stool, nucleicAcid,
				other);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleMaterial {\n");

		sb.append("    tissue: ").append(toIndentedString(tissue)).append("\n");
		sb.append("    wholeBlood: ").append(toIndentedString(wholeBlood)).append("\n");
		sb.append("    serum: ").append(toIndentedString(serum)).append("\n");
		sb.append("    plasma: ").append(toIndentedString(plasma)).append("\n");
		sb.append("    buffyCoat: ").append(toIndentedString(buffyCoat)).append("\n");
		sb.append("    FFPE: ").append(toIndentedString(FFPE)).append("\n");
		sb.append("    urine: ").append(toIndentedString(urine)).append("\n");
		sb.append("    saliva: ").append(toIndentedString(saliva)).append("\n");
		sb.append("    stool: ").append(toIndentedString(stool)).append("\n");
		sb.append("    nucleicAcid: ").append(toIndentedString(nucleicAcid)).append("\n");
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
