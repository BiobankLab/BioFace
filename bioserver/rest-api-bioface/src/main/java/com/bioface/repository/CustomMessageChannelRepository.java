package com.bioface.repository;

import java.util.List;

import com.bioface.model.EnumChannelPropertyType;
import com.bioface.model.Message;
import com.bioface.model.MessageChannel;
import com.bioface.model.ext.GetMessagesRequest;

public interface CustomMessageChannelRepository {

	void addMessage(String messageChannelId, Message message);

	List<MessageChannel> getUserMessageChannels(String user, List<String> userGroups);

	List<Message> getMessagesForChannel(String user, GetMessagesRequest request);

	List<MessageChannel> findForPropertyAndType(EnumChannelPropertyType enumChannelPropertyType,
			String channelPropertyId, List<String> userRoles, String user);
}
