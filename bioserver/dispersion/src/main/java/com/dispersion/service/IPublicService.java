package com.dispersion.service;

import org.springframework.stereotype.Service;

import com.dispersion.model.SamplesChartDataResponse;

@Service
public interface IPublicService {

	Long countSamples();

	SamplesChartDataResponse getSamplesChartData();

}