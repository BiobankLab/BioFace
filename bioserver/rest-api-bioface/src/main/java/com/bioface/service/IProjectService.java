package com.bioface.service;

import java.util.List;

import org.apache.solr.client.solrj.response.QueryResponse;

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

public interface IProjectService {

	BasicPaginationQueryResponse<Project> getUserProjects(String userId, BasicPaginationQueryRequest query);

	void createProject(String userId, CreateProjectRequest project) throws Exception;

	void updateProject(String userId, UpdateProjectRequest updateProjectRequest);

	void deleteProject(String userId, String _id);

	List<Project> getNewUserOwnProjects(String userId);

	void addQueryToProject(String userId, ProjectQueryRequest projectQueryRequest);

	void removeQueryFromProject(String userId, ProjectQueryRequest projectQueryRequest);

	Project checkProjectAuthorization(String userId, String projectId, boolean onlyInitiator);

	ProjectSampleQueryResponse executeProject(String userId, ProjectSampleQueryRequest projectQueryRequest,
			boolean saveHistory);
	
	List<SearchInDispersionQueryResponse> executeProjectWithDispersion(String user, ProjectSampleQueryRequest projectQueryRequest);

	Project registerProjectExecution(Project project, QueryResponse queryResponse, long dispLength);

	void requestProject(String user, String projectId, String toDispersion);

	void closeProject(String user, String projectId);

	void shareProject(String user, ShareProjectRequest request);

	List<String> fromBiobankList(String user, String projectId);

	void shareSamplesForProject(String user, ShareSamplesToProjectRequest request);

	List<String> getProjectUsers(String user, String projectId);

	Long countProjectSamplesFromDispersion(String user, ProjectSampleQueryRequest projectQueryRequest);

}
