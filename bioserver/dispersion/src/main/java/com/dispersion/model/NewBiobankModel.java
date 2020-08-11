package com.dispersion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewBiobankModel {

	@JsonProperty("biobankName")
	private String biobankName;

	@JsonProperty("biobankKey")
	private String biobankKey;

	@JsonProperty("accession")
	private BiobankAccessEnum accession;

	public String getBiobankName() {
		return biobankName;
	}

	public void setBiobankName(String biobankName) {
		this.biobankName = biobankName;
	}

	public String getBiobankKey() {
		return biobankKey;
	}

	public void setBiobankKey(String biobankKey) {
		this.biobankKey = biobankKey;
	}

	public BiobankAccessEnum getAccession() {
		return accession;
	}

	public void setAccession(BiobankAccessEnum accession) {
		this.accession = accession;
	}

	

}
