package com.dispersion.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dispersion.model.SamplesChartDataResponse;
import com.dispersion.service.IPublicService;


@Controller
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private IPublicService iPublicService;
	
	@RequestMapping(value = "/countSamples", method = RequestMethod.GET) 
	public ResponseEntity<Long> countSamples() {
		return new ResponseEntity<Long>(iPublicService.countSamples(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/chartData")
	public ResponseEntity<SamplesChartDataResponse> getSamplesChartData() {
		return new ResponseEntity<SamplesChartDataResponse>(iPublicService.getSamplesChartData(), HttpStatus.OK);
	}
	
}
