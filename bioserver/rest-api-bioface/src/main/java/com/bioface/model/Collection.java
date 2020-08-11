package com.bioface.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "collections")
public class Collection {

	@Id
	private String id;
	private String collectionId;
	private String biobankId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
