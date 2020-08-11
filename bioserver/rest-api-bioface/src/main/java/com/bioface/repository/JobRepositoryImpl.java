package com.bioface.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bioface.model.Job;
import com.bioface.model.JobStatus;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public class JobRepositoryImpl implements CustomJobRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Job> getNewJobs() {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(JobStatus.NEW));
		return mongoTemplate.find(query, Job.class);
	}

	@Override
	public BasicPaginationQueryResponse<Job> getUserJobs(String user, BasicPaginationQueryRequest request) {
		Query query = new Query();
		query.addCriteria(Criteria.where("user").is(user));
		query.with(request.getPagable());
		query.skip(query.getSkip() - request.getMaxRows());
		
		BasicPaginationQueryResponse<Job> response = new BasicPaginationQueryResponse<>();
		response.setResultList(mongoTemplate.find(query, Job.class));
		response.setRowsNum(mongoTemplate.count(query, Job.class));
		return response;
	}

}
