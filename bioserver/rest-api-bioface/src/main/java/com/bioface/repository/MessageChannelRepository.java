package com.bioface.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bioface.model.MessageChannel;

public interface MessageChannelRepository extends MongoRepository<MessageChannel, String>, CustomMessageChannelRepository {

}
