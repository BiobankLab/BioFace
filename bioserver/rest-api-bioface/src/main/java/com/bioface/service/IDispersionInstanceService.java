package com.bioface.service;

import java.util.List;

import com.bioface.model.DispersionInstance;
import com.bioface.model.Job;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public interface IDispersionInstanceService {

	List<DispersionInstance> getAllDispersionInstances();
	void saveDispersionInstance(DispersionInstance dispersionInstance);
	void removeDispersionInstance(String id);
	BasicPaginationQueryResponse<Job> getDispersionJobs(BasicPaginationQueryRequest request);
}
