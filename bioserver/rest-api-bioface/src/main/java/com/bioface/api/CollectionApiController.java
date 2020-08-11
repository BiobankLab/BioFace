package com.bioface.api;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bioface.model.Collection;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.model.ext.RemoveCollectionRequest;
import com.bioface.service.ICollectionService;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-07T16:16:28.926+02:00")

@Controller
@RequestMapping("/collection")
public class CollectionApiController {

	private static final Logger log = LoggerFactory.getLogger(CollectionApiController.class);

	@Autowired
	private ICollectionService iCollectionService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<BasicPaginationQueryResponse<Collection>> searchCollections(Principal principal,
			@RequestBody BasicPaginationQueryRequest request) {
		return new ResponseEntity<BasicPaginationQueryResponse<Collection>>(
				iCollectionService.searchCollections(principal.getName(), request), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteCollection(Principal principal, @RequestBody RemoveCollectionRequest request) {
		iCollectionService.deleteCollection(principal.getName(), request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
