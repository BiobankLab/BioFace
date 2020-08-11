package com.bioface.model;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Biobank
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-07T16:54:58.556+02:00")
@Document(collection = "biobank")
public class Biobank {
	@JsonProperty
	@Id
	private String id = null;

	@JsonProperty
	private String biobankId = null;

	@JsonProperty
	private String acronym = null;

	@JsonProperty
	private String name = null;

	@JsonProperty
	private String url = null;

	@JsonProperty
	private String juristicPerson = null;

	@JsonProperty
	private String country = null;

	@JsonProperty
	@Valid
	private List<ContactInformation> contactInformation = null;

	@Transient
	@JsonProperty
	private boolean canEdit;

	@Transient
	@JsonProperty
	private boolean canImport;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBiobankId() {
		return biobankId;
	}

	public void setBiobankId(String biobankId) {
		this.biobankId = biobankId;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJuristicPerson() {
		return juristicPerson;
	}

	public void setJuristicPerson(String juristicPerson) {
		this.juristicPerson = juristicPerson;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<ContactInformation> getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(List<ContactInformation> contactInformation) {
		this.contactInformation = contactInformation;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public boolean isCanImport() {
		return canImport;
	}

	public void setCanImport(boolean canImport) {
		this.canImport = canImport;
	}

}
