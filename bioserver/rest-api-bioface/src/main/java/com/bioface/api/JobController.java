package com.bioface.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bioface.model.Job;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.repository.CustomJobRepository;
import com.bioface.service.IDispersionInstanceService;

@Controller
@RequestMapping(value = "/job")
public class JobController {

	@Autowired
	private CustomJobRepository customJobRepository;
	
	@Autowired
	private IDispersionInstanceService iDispersionInstanceService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<BasicPaginationQueryResponse<Job>> getUserSampleJobs(Principal principal,
			@RequestBody BasicPaginationQueryRequest request) {
		return new ResponseEntity<BasicPaginationQueryResponse<Job>>(
				customJobRepository.getUserJobs(principal.getName(), request), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dispersion", method = RequestMethod.POST)
	public ResponseEntity<BasicPaginationQueryResponse<Job>> getDispersionSampleJobs(
			@RequestBody BasicPaginationQueryRequest request) {
		return new ResponseEntity<BasicPaginationQueryResponse<Job>>(
				iDispersionInstanceService.getDispersionJobs(request), HttpStatus.OK);
	}

}
