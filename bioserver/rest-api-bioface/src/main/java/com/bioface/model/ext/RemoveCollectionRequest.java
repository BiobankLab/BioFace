package com.bioface.model.ext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveCollectionRequest {

	@JsonProperty
	private String collectionId;

	@JsonProperty
	private String biobankId;

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getBiobankId() {
		return biobankId;
	}

	public void setBiobankId(String biobankId) {
		this.biobankId = biobankId;
	}

}
