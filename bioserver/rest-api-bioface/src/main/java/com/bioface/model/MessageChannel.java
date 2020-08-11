package com.bioface.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "messageChannel")
public class MessageChannel {

	@Id
	@JsonProperty
	private String id;

	@JsonProperty
	private String initiator;

	@JsonProperty
	private Date createdOn;

	@JsonProperty
	private EnumChannelPropertyType channelPropertyType;

	@JsonProperty
	private String channelPropertyId;

	@JsonProperty
	private List<String> accessGroups;

	@JsonProperty
	private List<Message> messages;

	@JsonProperty
	@Transient
	private Integer unreadedMessageCount;

	/**
	 * If not null it's channel only for one biobank <-> project
	 */
	@JsonProperty
	private String biobank;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<String> getAccessGroups() {
		return accessGroups;
	}

	public void setAccessGroups(List<String> accessGroups) {
		this.accessGroups = accessGroups;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public EnumChannelPropertyType getChannelPropertyType() {
		return channelPropertyType;
	}

	public void setChannelPropertyType(EnumChannelPropertyType channelPropertyType) {
		this.channelPropertyType = channelPropertyType;
	}

	public String getChannelPropertyId() {
		return channelPropertyId;
	}

	public void setChannelPropertyId(String channelPropertyId) {
		this.channelPropertyId = channelPropertyId;
	}

	public String getBiobank() {
		return biobank;
	}

	public void setBiobank(String biobank) {
		this.biobank = biobank;
	}

	public Integer getUnreadedMessageCount() {
		return unreadedMessageCount;
	}

	public void setUnreadedMessageCount(Integer unreadedMessageCount) {
		this.unreadedMessageCount = unreadedMessageCount;
	}

}
