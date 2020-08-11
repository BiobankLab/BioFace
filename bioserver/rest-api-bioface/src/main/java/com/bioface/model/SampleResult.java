package com.bioface.model;

import java.util.Map;

public class SampleResult {

	private String biobank;

	private Map<EnumAccess, Integer> resultMap;

	public String getBiobank() {
		return biobank;
	}

	public void setBiobank(String biobank) {
		this.biobank = biobank;
	}

	public Map<EnumAccess, Integer> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<EnumAccess, Integer> resultMap) {
		this.resultMap = resultMap;
	}

	

}
