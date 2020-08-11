package com.bioface.api;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bioface.model.Biobank;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.service.IBiobankService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-07T16:16:28.926+02:00")

@Controller
@RequestMapping("/biobank")
public class BiobankApiController {

	private static final Logger log = LoggerFactory.getLogger(BiobankApiController.class);

	@Autowired
	private IBiobankService iBiobankService;

	@ApiOperation(value = "Add Biobank", nickname = "addBiobank", notes = "", tags = { "biobank", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful added Biobank"),
			@ApiResponse(code = 400, message = "Bad request") })
	@RequestMapping(value = "/", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	@RequiresRoles("ADMIN")
	public ResponseEntity<Void> addBiobank(Principal principal,
			@ApiParam(value = "Biobank", required = true) @Valid @RequestBody Biobank biobank) {
		iBiobankService.addBiobank(principal.getName(), biobank);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@ApiOperation(value = "Update Biobank", nickname = "updateBiobank", notes = "", tags = { "biobank", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful updated Biobank"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Biobank not found") })
	@RequestMapping(value = "/", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<String> updateBiobank(Principal principal,
			@ApiParam(value = "Biobank", required = true) @Valid @RequestBody Biobank biobank) {
		iBiobankService.updateBiobank(principal.getName(), biobank);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@ApiOperation(value = "Search Biobank", nickname = "searchBiobank", notes = "", tags = { "biobank", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = ""), @ApiResponse(code = 400, message = "Bad request") })
	@RequestMapping(value = "/search", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<BasicPaginationQueryResponse<Biobank>> searchBiobanks(
			@RequestBody BasicPaginationQueryRequest biobankQuery, Principal principal) {
		return new ResponseEntity<BasicPaginationQueryResponse<Biobank>>(
				iBiobankService.searchBiobanks(principal.getName(), biobankQuery), HttpStatus.OK);
	}

}
