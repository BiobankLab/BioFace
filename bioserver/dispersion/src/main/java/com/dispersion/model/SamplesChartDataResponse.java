package com.dispersion.model;

import java.util.List;

public class SamplesChartDataResponse {

	private List<SampleChartData> samplesData;
	private Long samplesCount;

	public List<SampleChartData> getSamplesData() {
		return samplesData;
	}

	public void setSamplesData(List<SampleChartData> samplesData) {
		this.samplesData = samplesData;
	}

	public Long getSamplesCount() {
		return samplesCount;
	}

	public void setSamplesCount(Long samplesCount) {
		this.samplesCount = samplesCount;
	}

}
