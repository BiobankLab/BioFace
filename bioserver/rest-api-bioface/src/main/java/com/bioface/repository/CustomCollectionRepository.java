package com.bioface.repository;

import java.util.List;

import com.bioface.model.Collection;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public interface CustomCollectionRepository {

	BasicPaginationQueryResponse<Collection> getCollections(BasicPaginationQueryRequest query,
			List<String> accesibleBiobanks);

}
