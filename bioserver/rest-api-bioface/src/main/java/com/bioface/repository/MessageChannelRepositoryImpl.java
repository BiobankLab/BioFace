package com.bioface.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bioface.model.EnumChannelPropertyType;
import com.bioface.model.Message;
import com.bioface.model.MessageChannel;
import com.bioface.model.ext.GetMessagesRequest;
import com.mongodb.MongoClient;

public class MessageChannelRepositoryImpl implements CustomMessageChannelRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoClient mongoClient;

	@Override
	public void addMessage(String messageChannelId, Message message) {
		Update update = new Update();
		message.setReadedBy(new ArrayList<>());
		update.push("messages", message);
		Criteria criteria = Criteria.where("id").is(messageChannelId);

		mongoTemplate.updateFirst(Query.query(criteria), update, MessageChannel.class);
	}

	@Override
	public List<MessageChannel> getUserMessageChannels(String user, List<String> userGroups) {
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("initiator").is(user), Criteria.where("accessGroups").in(userGroups));
		Query query = new Query(criteria);

		return mongoTemplate.find(query, MessageChannel.class);
	}

	@Override
	public List<Message> getMessagesForChannel(String user, GetMessagesRequest request) {

		MatchOperation messageChannellMatch = Aggregation.match(Criteria.where("id").is(request.getMessageChannelId()));
		UnwindOperation unwindMessages = Aggregation.unwind("$messages");
		ProjectionOperation messagesProject = Aggregation.project("messages");
		LimitOperation limitOperation = Aggregation.limit(20);

		Aggregation agg = null;
		boolean firstRead = false;

		if (request.getLastestMessageDate() != null) {
			MatchOperation lastestMessagesMatch = Aggregation
					.match(Criteria.where("messages.date").gt(request.getLastestMessageDate()));
			SortOperation sortByMessageDate = Aggregation.sort(Sort.Direction.ASC, "messages.date");
			agg = newAggregation(messageChannellMatch, unwindMessages, sortByMessageDate, messagesProject,
					lastestMessagesMatch, group().push("messages").as("messages"));
		} else if (request.getEarliestMessageDate() != null) {
			MatchOperation lastestMessagesMatch = Aggregation
					.match(Criteria.where("messages.date").lt(request.getEarliestMessageDate()));
			SortOperation sortByMessageDate = Aggregation.sort(Sort.Direction.DESC, "messages.date");
			agg = newAggregation(messageChannellMatch, unwindMessages, sortByMessageDate, messagesProject,
					lastestMessagesMatch, limitOperation, group().push("messages").as("messages"));
		} else {
			firstRead = true;
			SortOperation sortByMessageDate = Aggregation.sort(Sort.Direction.DESC, "messages.date");
			agg = newAggregation(messageChannellMatch, unwindMessages, sortByMessageDate, messagesProject,
					limitOperation, group().push("messages").as("messages"));
		}

		AggregationResults<MessageChannel> mm = mongoTemplate.aggregate(agg, MessageChannel.class,
				MessageChannel.class);

		/**
		 * Set all messages as readed by user
		 */
		if (firstRead && !mm.getMappedResults().isEmpty()) {
			Update readedUpdate = new Update();
			Criteria channelCriteria = Criteria.where("id").is(request.getMessageChannelId());
			readedUpdate.addToSet("messages.$[].readedBy", user);
			mongoTemplate.updateFirst(Query.query(channelCriteria), readedUpdate, MessageChannel.class);
		}

		if (mm.getMappedResults().size() == 0) {
			return new ArrayList<Message>();
		}

		return mm.getMappedResults().get(0).getMessages();
	}

	@Override
	public List<MessageChannel> findForPropertyAndType(EnumChannelPropertyType enumChannelPropertyType,
			String channelPropertyId, List<String> userRoles, String user) {
		Criteria typeCriteria = new Criteria();
		typeCriteria.andOperator(Criteria.where("channelPropertyType").is(enumChannelPropertyType.getName()),
				Criteria.where("channelPropertyId").is(channelPropertyId));

		Criteria accessCriteria = new Criteria();
		accessCriteria.orOperator(Criteria.where("initiator").is(user), Criteria.where("accessGroups").in(userRoles));

		Criteria mainCriteria = new Criteria();
		mainCriteria.andOperator(typeCriteria, accessCriteria);

		Query query = new Query(mainCriteria);
		query.fields().exclude("messages");

		List<MessageChannel> userMessageChannels = mongoTemplate.find(query, MessageChannel.class);

		for (MessageChannel mc : userMessageChannels) {
			ScriptOperations scriptOps = mongoTemplate.scriptOps();

			Double res = (Double) scriptOps.call("getUnreadedMessagesCountForUser", mc.getId(), user);
			mc.setUnreadedMessageCount(res.intValue());
		}

		return userMessageChannels;
	}

}
