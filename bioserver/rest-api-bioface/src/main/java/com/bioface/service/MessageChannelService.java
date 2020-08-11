package com.bioface.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioface.exception.AuthorizationException;
import com.bioface.model.EnumChannelPropertyType;
import com.bioface.model.Message;
import com.bioface.model.MessageChannel;
import com.bioface.model.ext.AddMessageRequest;
import com.bioface.model.ext.GetMessagesRequest;
import com.bioface.repository.MessageChannelRepository;

@Service
public class MessageChannelService implements IMessageChannelService {

	@Autowired
	private MessageChannelRepository messageChannelRepository;

	@Autowired
	private IUserRoleService iUserRoleService;

	private static final Logger log = LoggerFactory.getLogger(MessageChannelService.class);

	@Override
	public void createMessageChannel(String user, MessageChannel messageChannel) {
		try {
			messageChannel.setInitiator(user);
			messageChannel.setCreatedOn(new Date());
			messageChannel.setMessages(new ArrayList<>());
			messageChannelRepository.save(messageChannel);
		} catch (Exception e) {
			log.error("Error while creating message channel", e);
			throw new RuntimeException("Error while creating message channel");
		}

	}

	@Override
	public void addMessage(String user, AddMessageRequest messageRequest) {
		checkChannelAuthorization(user, messageRequest.getMessageChannelId(), false);
		try {

			Message message = new Message();
			message.setAuthor(user);
			message.setContent(messageRequest.getContent());
			message.setDate(new Date());

			messageChannelRepository.addMessage(messageRequest.getMessageChannelId(), message);

		} catch (Exception e) {
			log.error("Error while adding message to messageChannel", e);
			throw new RuntimeException("Error while adding message to messageChannel");
		}
	}

	@Override
	public void removeMessageChannel(String user, String messageChannelId) {
		checkChannelAuthorization(user, messageChannelId, true);
		try {
			messageChannelRepository.deleteById(messageChannelId);
		} catch (Exception e) {
			log.error("Error while removing message channel", e);
			throw new RuntimeException("Error while removing message channel");
		}
	}

	@Override
	public List<MessageChannel> getUserMessageChannels(String user) {
		try {
			return messageChannelRepository.getUserMessageChannels(user, new ArrayList<String>()); // TODO: groups
		} catch (Exception e) {
			log.error("Error while getting message channel", e);
			throw new RuntimeException("Error while getting message channels for user");
		}
	}

	private MessageChannel checkChannelAuthorization(String user, String messageChannelId, boolean onlyInitiator) {
		try {
			MessageChannel messageChannel = messageChannelRepository.findById(messageChannelId).get();

			if (onlyInitiator) {
				if (!user.equals(messageChannel.getInitiator())) {
					throw new AuthorizationException("Authorization exception");
				}
			} else {
				if (!user.equals(messageChannel.getInitiator())
						&& !iUserRoleService.hasAnyRole(user, messageChannel.getAccessGroups())) {
					throw new AuthorizationException("Authorization exception");
				}
			}

			return messageChannel;

		} catch (NoSuchElementException nse) {
			throw new BadRequestException("Channel with given id doesn't exists");
		} catch (AuthorizationException e) {
			log.error("Unauthorized request for getting message channel, messageChannelId: " + messageChannelId
					+ " user: " + user, e);
			throw e;
		} catch (Exception e) {
			log.error("Unknow error while checking authorization message channel");
			throw e;
		}

	}

	@Override
	public List<MessageChannel> getMessageChannelsForProject(String user, String projectId) {

		try {
			List<String> userRoles = iUserRoleService.getAllUserRoles(user);

			List<MessageChannel> messageChannels = messageChannelRepository
					.findForPropertyAndType(EnumChannelPropertyType.project, projectId, userRoles, user);

			return messageChannels;
		} catch (Exception e) {
			log.error("Error while getting message channels for project", e);
			throw new RuntimeException("Error while getting message channels for project");
		}
	}

	@Override
	public List<Message> getMessagesForChannel(String user, GetMessagesRequest request) {
		checkChannelAuthorization(user, request.getMessageChannelId(), false);
		try {
			return messageChannelRepository.getMessagesForChannel(user, request);
		} catch (Exception e) {
			throw e;
		}
	}

}
