package com.bioface.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bioface.model.Biobank;

public interface BiobankRepository extends MongoRepository<Biobank, String>, CustomBiobankRepository {
	
}
