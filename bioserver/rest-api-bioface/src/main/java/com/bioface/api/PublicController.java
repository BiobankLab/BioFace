package com.bioface.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bioface.model.SamplesChartDataResponse;
import com.bioface.service.IPublicService;

@Controller
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private Environment env;

	@Autowired
	private IPublicService iPublicService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/keycloakUrl")
	public ResponseEntity<String> getKeycloakAuthUrl() {
		return new ResponseEntity<String>(env.getProperty("ux.keycloak.auth-server-url"), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/chartData")
	public ResponseEntity<SamplesChartDataResponse> getSamplesChartData() {
		return new ResponseEntity<SamplesChartDataResponse>(iPublicService.getSamplesChartData(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/dispersionChartData")
	public ResponseEntity<SamplesChartDataResponse> getDispersionSamplesChartData() {
		return new ResponseEntity<SamplesChartDataResponse>(iPublicService.getDispersionSamplesChartData(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/biobanksCount")
	public ResponseEntity<Long> getBiobanksCount() {
		return new ResponseEntity<Long>(iPublicService.getBiobanksCount(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/dispersionInstancesCount")
	public ResponseEntity<Long> getDispersionInstancesCount() {
		return new ResponseEntity<Long>(iPublicService.getDispersionInstancesCount(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/dispersionSamplesCount")
	public ResponseEntity<Long> getDispersionSamplesCount() {
		return new ResponseEntity<Long>(iPublicService.getDispersionSamplesCount(), HttpStatus.OK);
	}
	
}
