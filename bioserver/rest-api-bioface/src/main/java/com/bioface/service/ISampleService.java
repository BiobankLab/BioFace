package com.bioface.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.SearchInDispersionQueryResponse;
import com.bioface.model.ext.UpdateSampleRequest;

@Service
public interface ISampleService {

	JSONObject getSamplesForQuery(String user, BasicPaginationQueryRequest queryRequest);

	void addFileToJobQueue(String user, MultipartFile file, String biobankId, Boolean upload) throws IOException;

	String getAccessSolrQueryPart(String user);

	void updateSampleAccess(String user, UpdateSampleRequest updateSampleRequest);

	List<String> getBiofaceCoreFieldsList();
	
	String getFieldsOrder();
	
	String createToken();
	
	List<SearchInDispersionQueryResponse> getDispersionSamplesForQuery(BasicPaginationQueryRequest queryRequest);

	Long countDispersionSamplesForQuery(BasicPaginationQueryRequest queryRequest);
}
