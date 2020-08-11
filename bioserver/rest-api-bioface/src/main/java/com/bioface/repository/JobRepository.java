package com.bioface.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bioface.model.Job;

public interface JobRepository extends MongoRepository<Job, String> {

}
