package com.bioface.model.ext;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicPaginationQueryResponse<T> {

	@JsonProperty
	private List<T> resultList;
	
	@JsonProperty
	private Long rowsNum;

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public Long getRowsNum() {
		return rowsNum;
	}

	public void setRowsNum(Long rowsNum) {
		this.rowsNum = rowsNum;
	}

}
