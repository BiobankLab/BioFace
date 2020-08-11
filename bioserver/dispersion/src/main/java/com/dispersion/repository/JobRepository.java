package com.dispersion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dispersion.model.Job;

public interface JobRepository extends MongoRepository<Job, String>, CustomJobRepository {

}
