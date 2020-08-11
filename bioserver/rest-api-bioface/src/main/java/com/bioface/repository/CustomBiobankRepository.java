package com.bioface.repository;

import java.util.List;

import com.bioface.model.Biobank;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public interface CustomBiobankRepository {
	
	BasicPaginationQueryResponse<Biobank> searchBiobanks(BasicPaginationQueryRequest biobankQuery, List<String> biobankIdsAvailable);
	BasicPaginationQueryResponse<Biobank> getAllBiobanks(BasicPaginationQueryRequest biobankQuery);
	boolean biobankWithIdExists(String biobankId);
}
