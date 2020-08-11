package com.dispersion.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dispersion.model.BasicPaginationQueryRequest;
import com.dispersion.model.BasicPaginationQueryResponse;
import com.dispersion.model.Job;
import com.dispersion.repository.JobRepository;

@Controller
@RequestMapping(value = "/dispersionJob")
public class JobController {

	@Autowired
	private JobRepository customJobRepository;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<BasicPaginationQueryResponse<Job>> getUserSampleJobs(@RequestBody BasicPaginationQueryRequest request) {
		return new ResponseEntity<BasicPaginationQueryResponse<Job>>(
				customJobRepository.getUserJobs(request), HttpStatus.OK);
	}
}
