package com.dispersion.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.dispersion.model.BasicPaginationQueryRequest;
import com.dispersion.service.ISamplesService;

@Controller
@RequestMapping("/dispersionSample")
public class SamplesController {

	@Autowired
	private ISamplesService iSamplesService;

	@CrossOrigin
	@RequestMapping(value = "/countSamples", produces =  { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Long> countSamples(Principal principal, @RequestBody BasicPaginationQueryRequest queryRequest) {
			return new ResponseEntity<Long>(
					iSamplesService.countSamplesForQuery(principal.getName(), queryRequest), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/searchSample", produces =  { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<String> getSamples(Principal principal, @RequestBody BasicPaginationQueryRequest queryRequest) {
		try {
			return new ResponseEntity<String>(
					iSamplesService.getSamplesForQuery(principal.getName(), queryRequest).toString(), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(e.getMessage());
		}
	}

	@RequestMapping(value = "/addSamples", method = RequestMethod.POST)
	public ResponseEntity<Void> addSamples(@RequestParam("file") MultipartFile file) throws Exception {
		iSamplesService.importSamples(file);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
