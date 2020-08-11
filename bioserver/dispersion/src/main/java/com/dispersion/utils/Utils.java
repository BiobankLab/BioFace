package com.dispersion.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocumentList;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
	
	public static String processSolrQueries(List<String> queries) {
		
		StringBuilder solrQueryBuilder = new StringBuilder();
		for (int i = 0; i < queries.size(); i++) {
			solrQueryBuilder.append("(").append(queries.get(i)).append(")");
			if (i < queries.size() - 1) {
				solrQueryBuilder.append(" OR ");
			}
		}
		return solrQueryBuilder.toString();
	}
	
	public static JSONObject parseSolrResult(SolrDocumentList solrDocumentList) throws Exception {

		try {
			JSONObject jsonResult = new JSONObject();
			List<Map> resultList = new ArrayList<>();
			resultList.addAll(solrDocumentList);
			jsonResult.put("result", resultList);
			jsonResult.put("numFound", solrDocumentList.getNumFound());
			return jsonResult;
		} catch (JSONException e) {
			throw e;
		}
	}

}
