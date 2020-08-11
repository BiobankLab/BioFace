package com.bioface.repository;

import java.util.List;

import com.bioface.model.Job;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public interface CustomJobRepository {

	/**
	 * Return jobs to queue
	 * 
	 * @return
	 */
	List<Job> getNewJobs();

	/**
	 * Return user jobs
	 * 
	 * @param user
	 * @return
	 */
	BasicPaginationQueryResponse<Job> getUserJobs(String user, BasicPaginationQueryRequest request);

}
