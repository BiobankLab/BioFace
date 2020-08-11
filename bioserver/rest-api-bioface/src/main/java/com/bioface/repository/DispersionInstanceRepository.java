package com.bioface.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bioface.model.DispersionInstance;

public interface DispersionInstanceRepository extends MongoRepository<DispersionInstance, String> {

}
