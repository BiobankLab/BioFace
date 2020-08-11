package com.bioface.model.ext;

import com.bioface.model.EnumChannelPropertyType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMessageChannelRequest {

	@JsonProperty
	private String group;

	@JsonProperty
	private EnumChannelPropertyType channelType;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public EnumChannelPropertyType getChannelType() {
		return channelType;
	}

	public void setChannelType(EnumChannelPropertyType channelType) {
		this.channelType = channelType;
	}

}
