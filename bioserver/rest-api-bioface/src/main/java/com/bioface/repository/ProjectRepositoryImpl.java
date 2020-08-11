package com.bioface.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bioface.model.Project;
import com.bioface.model.ProjectHistory;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public class ProjectRepositoryImpl implements CustomProjectRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public BasicPaginationQueryResponse<Project> getUserProjects(String userId,
			BasicPaginationQueryRequest projectQuery, List<String> projectsByGroup) {
		Query query = new Query();
		Criteria userIdCriteria = Criteria.where("userId").is(userId);
		// "i" added for case insensitive
		String keyword = projectQuery.getQuery();

		Criteria keywordCriteria = null;
		if (keyword != null && !keyword.isEmpty()) {
			keywordCriteria = new Criteria();
			keywordCriteria.orOperator(Criteria.where("name").regex(keyword, "i"),
					Criteria.where("description").regex(keyword, "i"));
		}

		Criteria projectByGroupCriteria = null;
		if (projectsByGroup != null && !projectsByGroup.isEmpty()) {
			projectByGroupCriteria = Criteria.where("rolesIncluded").in(projectsByGroup);
		}

		Criteria accessCriteria = new Criteria();

		if (projectByGroupCriteria != null) {
			accessCriteria.orOperator(userIdCriteria, projectByGroupCriteria);
		} else {
			accessCriteria = userIdCriteria;
		}

		Criteria mainCriteria = new Criteria();
		if (keywordCriteria != null) {
			mainCriteria.andOperator(keywordCriteria, accessCriteria);
		} else {
			mainCriteria = accessCriteria;
		}

		query.addCriteria(mainCriteria);
		// query.with(projectQuery.getPagable());

		BasicPaginationQueryResponse<Project> response = new BasicPaginationQueryResponse<Project>();
		response.setResultList(mongoTemplate.find(query, Project.class));
		response.setRowsNum(mongoTemplate.count(query, Project.class));

		return response;
	}

	@Override
	public void addQueryToProject(String projectId, String query) {
		Update update = new Update();
		update.push("queries", query);
		Criteria criteria = Criteria.where("id").is(projectId);
		mongoTemplate.updateFirst(Query.query(criteria), update, Project.class);
	}

	@Override
	public boolean queryExistsInProject(String projectId, String queryString) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(projectId).andOperator(Criteria.where("queries").in(queryString)));

		return mongoTemplate.exists(query, Project.class);
	}

	@Override
	public void removeQueryFromProjects(String projectId, String query) {
		Update update = new Update();
		update.pull("queries", query);
		Criteria criteria = Criteria.where("id").is(projectId);
		mongoTemplate.updateFirst(Query.query(criteria), update, Project.class);
	}

	@Override
	public void updateProjectExecutionHistory(Project project, ProjectHistory projectHistory) {
		Update update = new Update();
		update.push("projectHistory", projectHistory);
		Criteria criteria = Criteria.where("id").is(project.getId());
		mongoTemplate.updateFirst(Query.query(criteria), update, Project.class);

	}

	@Override
	public boolean existsProjectWithName(String name) {
		Criteria criteria = Criteria.where("name").regex("^" + name + "$", "i");

		return mongoTemplate.exists(Query.query(criteria), Project.class);
	}

	@Override
	public void addSharedBiobank(String projectId, String biobank) {
		Criteria criteria = Criteria.where("id").is(projectId);
		Update update = new Update();
		update.push("biobankShared", biobank);
		
		mongoTemplate.updateFirst(Query.query(criteria), update, Project.class);
		
	}

}
