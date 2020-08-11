package com.bioface.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bioface.model.Collection;

public interface CollectionRepository extends MongoRepository<Collection, String>, CustomCollectionRepository {
	Long deleteByCollectionIdAndBiobankId(String collectionId, String biobankId);
}
