package com.dispersion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiobankListElement {

	@JsonProperty
	private String name;

	@JsonProperty
	private BiobankAccessEnum accession;

	public BiobankListElement() {
		super();
	}

	public BiobankListElement(String name, BiobankAccessEnum accession) {
		super();
		this.name = name;
		this.accession = accession;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BiobankAccessEnum getAccession() {
		return accession;
	}

	public void setAccession(BiobankAccessEnum accession) {
		this.accession = accession;
	}

}
