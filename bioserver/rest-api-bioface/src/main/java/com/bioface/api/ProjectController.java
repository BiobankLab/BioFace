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

import com.bioface.model.Project;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.model.ext.CreateProjectRequest;
import com.bioface.model.ext.ProjectQueryRequest;
import com.bioface.model.ext.ProjectSampleQueryRequest;
import com.bioface.model.ext.ProjectSampleQueryResponse;
import com.bioface.model.ext.SearchInDispersionQueryResponse;
import com.bioface.model.ext.ShareProjectRequest;
import com.bioface.model.ext.ShareSamplesToProjectRequest;
import com.bioface.model.ext.UpdateProjectRequest;
import com.bioface.service.IProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private IProjectService iProjectService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<BasicPaginationQueryResponse<Project>> getUserProjects(Principal principal,
			@RequestBody BasicPaginationQueryRequest basicPaginationQuery) {
		return new ResponseEntity<BasicPaginationQueryResponse<Project>>(
				iProjectService.getUserProjects(principal.getName(), basicPaginationQuery), HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Project>> getAllUserProjects(Principal principal) {
		return new ResponseEntity<List<Project>>(iProjectService.getNewUserOwnProjects(principal.getName()),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> createProject(Principal principal, @RequestBody CreateProjectRequest projectRequest)
			throws Exception {
		iProjectService.createProject(principal.getName(), projectRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProject(Principal principal,
			@RequestBody UpdateProjectRequest updateProjectRequest) {
		iProjectService.updateProject(principal.getName(), updateProjectRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{projectId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProject(Principal principal, @PathVariable String projectId) {
		iProjectService.deleteProject(principal.getName(), projectId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/queryAdd", method = RequestMethod.POST)
	public ResponseEntity<Void> addQueryToProject(Principal principal,
			@RequestBody ProjectQueryRequest projectQueryRequest) {
		iProjectService.addQueryToProject(principal.getName(), projectQueryRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/queryRemove", method = RequestMethod.POST)
	public ResponseEntity<Void> removeQueryFromProject(Principal principal,
			@RequestBody ProjectQueryRequest projectQueryRequest) {
		iProjectService.removeQueryFromProject(principal.getName(), projectQueryRequest);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/execute/", method = RequestMethod.POST)
	public ResponseEntity<ProjectSampleQueryResponse> executeProject(Principal principal,
			@RequestBody ProjectSampleQueryRequest projectQueryRequest) {
		return new ResponseEntity<ProjectSampleQueryResponse>(
				iProjectService.executeProject(principal.getName(), projectQueryRequest, true), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/executeDisperion/", method = RequestMethod.POST)
	public ResponseEntity<List<SearchInDispersionQueryResponse>>  executeProjectWithDispersion(Principal principal,
			@RequestBody ProjectSampleQueryRequest projectQueryRequest) {
		return ResponseEntity.ok().body(iProjectService.executeProjectWithDispersion(principal.getName(), projectQueryRequest));
	}

	@RequestMapping(value = "/request/{projectId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> requestProject(Principal principal, @PathVariable String projectId, @RequestBody String toDispersion) {
		iProjectService.requestProject(principal.getName(), projectId, toDispersion);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/close/{projectId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> closeProject(Principal principal, @PathVariable String projectId) {
		iProjectService.closeProject(principal.getName(), projectId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public ResponseEntity<Void> shareProject(Principal principal, @RequestBody ShareProjectRequest request) {
		iProjectService.shareProject(principal.getName(), request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/samples", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ProjectSampleQueryResponse> getSamplesForProject(Principal principal,
			@RequestBody ProjectSampleQueryRequest projectQueryRequest) {
		return new ResponseEntity<ProjectSampleQueryResponse>(
				iProjectService.executeProject(principal.getName(), projectQueryRequest, false), HttpStatus.OK);
	}

	@RequestMapping(value = "/fromBiobank/{projectId}")
	public ResponseEntity<List<String>> isFromBiobank(Principal principal, @PathVariable String projectId) {
		return new ResponseEntity<List<String>>(iProjectService.fromBiobankList(principal.getName(), projectId),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/shareSamples", method = RequestMethod.POST)
	public ResponseEntity<Void> shareSamplesForProject(Principal principal,
			@RequestBody ShareSamplesToProjectRequest request) {
		iProjectService.shareSamplesForProject(principal.getName(), request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{projectId}", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getProjectUsers(Principal principal, @PathVariable String projectId) {
		return new ResponseEntity<List<String>>(iProjectService.getProjectUsers(principal.getName(), projectId),
				HttpStatus.OK);
	}

}
