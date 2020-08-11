package com.dispersion.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dispersion.model.Job;
import com.dispersion.model.JobStatus;
import com.dispersion.model.BasicPaginationQueryRequest;
import com.dispersion.model.BasicPaginationQueryResponse;

public class JobRepositoryImpl implements CustomJobRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private Environment environment;

	@Override
	public List<Job> getNewJobs() {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(JobStatus.NEW));
		return mongoTemplate.find(query, Job.class);
	}

	@Override
	public BasicPaginationQueryResponse<Job> getUserJobs(BasicPaginationQueryRequest request) {
		String user = environment.getProperty("institution.name");
		Query query = new Query();
		query.addCriteria(Criteria.where("biobankId").is(user));
		query.with(request.getPagable());
		query.skip(query.getSkip() - request.getMaxRows());
		
		BasicPaginationQueryResponse<Job> response = new BasicPaginationQueryResponse<>();
		response.setResultList(mongoTemplate.find(query, Job.class));
		response.setRowsNum(mongoTemplate.count(query, Job.class));
		return response;
	}

}
