package com.bioface.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.SearchInDispersionQueryResponse;
import com.bioface.model.ext.UpdateSampleRequest;
import com.bioface.service.ISampleService;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-07T16:16:28.926+02:00")

@Controller
@RequestMapping("/sample")
public class SampleApiController {

	@Autowired
	private ISampleService iSampleService;

	@RequestMapping(value = "/searchSample", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<String> getSamples(Principal principal,
			@RequestBody BasicPaginationQueryRequest queryRequest) {
		try {
			return new ResponseEntity<String>(
					iSampleService.getSamplesForQuery(principal.getName(), queryRequest).toString(), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/searchSample/dispersion", method = RequestMethod.POST)
	public ResponseEntity<List<SearchInDispersionQueryResponse>> getSamplesFromDispersion(@RequestBody BasicPaginationQueryRequest queryRequest) {
		return ResponseEntity.ok().body(iSampleService.getDispersionSamplesForQuery(queryRequest));
	}

	@RequestMapping(value = "/addSamples/{biobankId}", method = RequestMethod.POST)
	public ResponseEntity<Void> addSamples(Principal principal, @RequestParam("file") MultipartFile file, @RequestParam("upload") Boolean upload,
			@PathVariable String biobankId) throws Exception {
		iSampleService.addFileToJobQueue(principal.getName(), file, biobankId, upload);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/fields", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getSolrFieldsList() {
		return new ResponseEntity<List<String>>(iSampleService.getBiofaceCoreFieldsList(), HttpStatus.OK);
	}

	@RequestMapping(value = "/fields/order", method = RequestMethod.GET)
	public ResponseEntity<String> getFieldsOrder() {
		return new ResponseEntity<String>(iSampleService.getFieldsOrder(), HttpStatus.OK);
	}

	/**
	 * @author mbucko 2018-09-17 Temporary disabled
	 */
	// @RequestMapping(value = "/sample/access", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSampleAccess(Principal principal,
			@RequestBody UpdateSampleRequest updateSampleRequest) {
		iSampleService.updateSampleAccess(principal.getName(), updateSampleRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
