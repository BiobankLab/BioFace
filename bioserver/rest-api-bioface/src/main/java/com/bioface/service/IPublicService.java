package com.bioface.service;

import com.bioface.model.SamplesChartDataResponse;

public interface IPublicService {
	SamplesChartDataResponse getSamplesChartData();

	SamplesChartDataResponse getDispersionSamplesChartData();
	
	Long getBiobanksCount();
	
	Long getDispersionInstancesCount();

	Long getDispersionSamplesCount();
}
