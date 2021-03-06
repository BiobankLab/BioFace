package com.dispersion.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicPaginationQueryRequest {

	@JsonProperty
	private String query;

	@JsonProperty
	private Integer page;

	@JsonProperty
	private Integer maxRows;

	@JsonProperty
	private boolean sortDesc;

	@JsonProperty
	private String sortField;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(Integer maxRows) {
		this.maxRows = maxRows;
	}

	public boolean isSortDesc() {
		return sortDesc;
	}

	public void setSortDesc(boolean sortDesc) {
		this.sortDesc = sortDesc;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	public Pageable getPagable() {
		Pageable pageable = null;
		if(this.sortField != null && !this.sortField.isEmpty()) {
			Direction direction = isSortDesc() ? Direction.DESC : Direction.ASC;
			pageable = PageRequest.of(this.page, this.maxRows, new Sort(direction, this.sortField));
		} else {
			pageable = PageRequest.of(this.page, this.maxRows);
		}
		
		return pageable;
	}

}
