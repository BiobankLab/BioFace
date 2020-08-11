package com.bioface.service;

import java.util.List;

import com.bioface.model.Message;
import com.bioface.model.MessageChannel;
import com.bioface.model.ext.AddMessageRequest;
import com.bioface.model.ext.GetMessagesRequest;

public interface IMessageChannelService {

	/**
	 * Create message channel
	 * 
	 * @author mbucko
	 * @param user
	 * @param messageChannel
	 */
	void createMessageChannel(String user, MessageChannel messageChannel);

	/**
	 * Add message to existing message channel
	 * 
	 * @author mbucko
	 * @param user
	 * @param messageRequest
	 */
	void addMessage(String user, AddMessageRequest messageRequest);

	/**
	 * Remove message channel
	 * 
	 * @author mbucko
	 * @param user
	 * @param messageChannelId
	 */
	void removeMessageChannel(String user, String messageChannelId);

	/**
	 * Get all message channels which are available for user
	 * 
	 * @author mbucko
	 * @param user
	 * @return
	 */
	List<MessageChannel> getUserMessageChannels(String user);

	/**
	 * Message Channel for project
	 * 
	 * @author mbucko
	 * @param user
	 * @param projectId
	 * @return
	 */
	List<MessageChannel> getMessageChannelsForProject(String user, String projectId);

	/**
	 * Messages for chat channel
	 * 
	 * @author mbucko
	 * @param channelId
	 * @return
	 */
	List<Message> getMessagesForChannel(String user, GetMessagesRequest request);

}
