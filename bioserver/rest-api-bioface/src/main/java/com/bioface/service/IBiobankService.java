package com.bioface.service;

import java.util.List;

import com.bioface.model.Biobank;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public interface IBiobankService {

	List<Biobank> getAllBiobanks(String user);

	void addBiobank(String user, Biobank biobank);

	void updateBiobank(String user, Biobank biobank);

	BasicPaginationQueryResponse<Biobank> searchBiobanks(String user, BasicPaginationQueryRequest biobankQuery);
}
