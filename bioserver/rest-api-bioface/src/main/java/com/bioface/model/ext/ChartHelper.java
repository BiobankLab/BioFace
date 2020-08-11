package com.bioface.model.ext;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.response.PivotField;

public class ChartHelper {

	private List<PivotField> pivot = new ArrayList<>();

	private int count;

	public List<PivotField> getPivot() {
		return pivot;
	}

	public void setPivot(List<PivotField> pivot) {
		this.pivot = pivot;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void addToCount(int count) {
		this.count += count;
	}

}
