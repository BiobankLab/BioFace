package com.bioface.model.ext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddMessageRequest {

	@JsonProperty
	private String messageChannelId;
	@JsonProperty
	private String content;

	public String getMessageChannelId() {
		return messageChannelId;
	}

	public void setMessageChannelId(String messageChannelId) {
		this.messageChannelId = messageChannelId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
