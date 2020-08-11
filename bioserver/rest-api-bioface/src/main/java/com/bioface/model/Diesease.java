package com.bioface.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.threeten.bp.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Diesease
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-07T15:53:44.945+02:00")

public class Diesease {
	@JsonProperty("date")
	private LocalDate date = null;

	@JsonProperty("ontology")
	private String ontology = null;

	@JsonProperty("ontologyVersion")
	private String ontologyVersion = null;

	@JsonProperty("ontologyCode")
	private String ontologyCode = null;

	@JsonProperty("ontologyDescription")
	private String ontologyDescription = null;

	@JsonProperty("freeInformation")
	private String freeInformation = null;

	public Diesease date(LocalDate date) {
		this.date = date;
		return this;
	}

	/**
	 * Get date
	 * 
	 * @return date
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Diesease ontology(String ontology) {
		this.ontology = ontology;
		return this;
	}

	/**
	 * Get ontology
	 * 
	 * @return ontology
	 **/
	@ApiModelProperty(value = "")

	public String getOntology() {
		return ontology;
	}

	public void setOntology(String ontology) {
		this.ontology = ontology;
	}

	public Diesease ontologyVersion(String ontologyVersion) {
		this.ontologyVersion = ontologyVersion;
		return this;
	}

	/**
	 * Get ontologyVersion
	 * 
	 * @return ontologyVersion
	 **/
	@ApiModelProperty(value = "")

	public String getOntologyVersion() {
		return ontologyVersion;
	}

	public void setOntologyVersion(String ontologyVersion) {
		this.ontologyVersion = ontologyVersion;
	}

	public Diesease ontologyCode(String ontologyCode) {
		this.ontologyCode = ontologyCode;
		return this;
	}

	/**
	 * Get ontologyCode
	 * 
	 * @return ontologyCode
	 **/
	@ApiModelProperty(value = "")

	public String getOntologyCode() {
		return ontologyCode;
	}

	public void setOntologyCode(String ontologyCode) {
		this.ontologyCode = ontologyCode;
	}

	public Diesease ontologyDescription(String ontologyDescription) {
		this.ontologyDescription = ontologyDescription;
		return this;
	}

	/**
	 * Get ontologyDescription
	 * 
	 * @return ontologyDescription
	 **/
	@ApiModelProperty(value = "")

	public String getOntologyDescription() {
		return ontologyDescription;
	}

	public void setOntologyDescription(String ontologyDescription) {
		this.ontologyDescription = ontologyDescription;
	}

	public Diesease freeInformation(String freeInformation) {
		this.freeInformation = freeInformation;
		return this;
	}

	/**
	 * Get freeInformation
	 * 
	 * @return freeInformation
	 **/
	@ApiModelProperty(value = "")

	public String getFreeInformation() {
		return freeInformation;
	}

	public void setFreeInformation(String freeInformation) {
		this.freeInformation = freeInformation;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Diesease diesease = (Diesease) o;
		return Objects.equals(this.date, diesease.date) && Objects.equals(this.ontology, diesease.ontology)
				&& Objects.equals(this.ontologyVersion, diesease.ontologyVersion)
				&& Objects.equals(this.ontologyCode, diesease.ontologyCode)
				&& Objects.equals(this.ontologyDescription, diesease.ontologyDescription)
				&& Objects.equals(this.freeInformation, diesease.freeInformation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, ontology, ontologyVersion, ontologyCode, ontologyDescription, freeInformation);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Diesease {\n");

		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    ontology: ").append(toIndentedString(ontology)).append("\n");
		sb.append("    ontologyVersion: ").append(toIndentedString(ontologyVersion)).append("\n");
		sb.append("    ontologyCode: ").append(toIndentedString(ontologyCode)).append("\n");
		sb.append("    ontologyDescription: ").append(toIndentedString(ontologyDescription)).append("\n");
		sb.append("    freeInformation: ").append(toIndentedString(freeInformation)).append("\n");
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
