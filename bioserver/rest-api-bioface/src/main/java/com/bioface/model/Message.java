package com.bioface.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

	@JsonProperty
	private String author;

	@JsonProperty
	private Date date;

	@JsonProperty
	private String content;

	@JsonProperty
	private List<String> readedBy;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getReadedBy() {
		return readedBy;
	}

	public void setReadedBy(List<String> readedBy) {
		this.readedBy = readedBy;
	}

}
