package com.bioface.service;

import com.bioface.model.Collection;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.model.ext.RemoveCollectionRequest;

public interface ICollectionService {

	BasicPaginationQueryResponse<Collection> searchCollections(String user, BasicPaginationQueryRequest request);

	void deleteCollection(String user, RemoveCollectionRequest request);
}
