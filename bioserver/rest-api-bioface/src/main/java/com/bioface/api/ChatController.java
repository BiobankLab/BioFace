package com.bioface.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bioface.model.Message;
import com.bioface.model.MessageChannel;
import com.bioface.model.ext.AddMessageRequest;
import com.bioface.model.ext.GetMessagesRequest;
import com.bioface.service.IMessageChannelService;

@Controller
@RequestMapping(value = "/chat")
public class ChatController {

	@Autowired
	private IMessageChannelService iMessageChannelService;

	@RequestMapping(method = RequestMethod.GET, value = "/project/{projectId}")
	public ResponseEntity<List<MessageChannel>> getMessageChannelsForProject(Principal principal,
			@PathVariable String projectId) {
		return new ResponseEntity<List<MessageChannel>>(
				iMessageChannelService.getMessageChannelsForProject(principal.getName(), projectId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/message")
	public ResponseEntity<Void> addMessage(Principal principal, @RequestBody AddMessageRequest addMessageRequest) {
		iMessageChannelService.addMessage(principal.getName(), addMessageRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/message/read")
	public ResponseEntity<List<Message>> getMessagesForChannel(Principal principal,
			@RequestBody GetMessagesRequest getMessagesRequest) {
		return new ResponseEntity<List<Message>>(
				iMessageChannelService.getMessagesForChannel(principal.getName(), getMessagesRequest), HttpStatus.OK);
	}
}
