package com.dispersion.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dispersion.model.BasicPaginationQueryRequest;

@Service
public interface ISamplesService {
	
	long countSamplesForQuery(String biobank, BasicPaginationQueryRequest queryRequest);
	
	JSONObject getSamplesForQuery(String biobank, BasicPaginationQueryRequest queryRequest);
	
	void importSamples(MultipartFile file);

}