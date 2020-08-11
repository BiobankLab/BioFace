package com.bioface.repository;

import java.util.List;

import com.bioface.model.Project;
import com.bioface.model.ProjectHistory;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public interface CustomProjectRepository {

	BasicPaginationQueryResponse<Project> getUserProjects(String userId, BasicPaginationQueryRequest request,
			List<String> projectsByGroups);

	void addQueryToProject(String projectId, String query);

	boolean queryExistsInProject(String projectId, String queryString);

	void removeQueryFromProjects(String projectId, String query);

	void updateProjectExecutionHistory(Project project, ProjectHistory projectHistory);

	boolean existsProjectWithName(String name);

	void addSharedBiobank(String projectId, String biobank);
}
