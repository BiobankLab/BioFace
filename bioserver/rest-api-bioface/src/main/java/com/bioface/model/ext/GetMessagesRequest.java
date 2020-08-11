package com.bioface.model.ext;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMessagesRequest {

	@JsonProperty
	private String messageChannelId;

	@JsonProperty
	private Date earliestMessageDate;

	@JsonProperty
	private Date lastestMessageDate;

	@JsonProperty
	private Integer page;

	public String getMessageChannelId() {
		return messageChannelId;
	}

	public void setMessageChannelId(String messageChannelId) {
		this.messageChannelId = messageChannelId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Date getEarliestMessageDate() {
		return earliestMessageDate;
	}

	public void setEarliestMessageDate(Date earliestMessageDate) {
		this.earliestMessageDate = earliestMessageDate;
	}

	public Date getLastestMessageDate() {
		return lastestMessageDate;
	}

	public void setLastestMessageDate(Date lastestMessageDate) {
		this.lastestMessageDate = lastestMessageDate;
	}

}
