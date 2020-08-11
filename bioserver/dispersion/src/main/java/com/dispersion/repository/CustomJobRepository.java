package com.dispersion.repository;

import java.util.List;

import com.dispersion.model.Job;
import com.dispersion.model.BasicPaginationQueryRequest;
import com.dispersion.model.BasicPaginationQueryResponse;

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
	BasicPaginationQueryResponse<Job> getUserJobs(BasicPaginationQueryRequest request);

}
