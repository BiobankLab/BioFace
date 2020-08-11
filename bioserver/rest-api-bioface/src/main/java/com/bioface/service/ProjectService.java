package com.bioface.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioface.exception.AuthorizationException;
import com.bioface.exception.BadRequestException;
import com.bioface.model.EnumAccess;
import com.bioface.model.EnumChannelPropertyType;
import com.bioface.model.EnumProjectAccessType;
import com.bioface.model.EnumProjectStatus;
import com.bioface.model.MessageChannel;
import com.bioface.model.Project;
import com.bioface.model.ProjectHistory;
import com.bioface.model.SampleResult;
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
import com.bioface.repository.ProjectRepository;
import com.bioface.utils.Utils;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private IMessageChannelService iMessageChannelService;
	
	@Autowired
	private ISampleService iSampleService;

	@Autowired
	private SolrClient solrClient;

	@Autowired
	private IUserRoleService iUserRoleService;

	private static final int MAX_PROJECT_NAME_LENGTH = 70;
	private static final int MIN_PROJECT_NAME_LENGTH = 3;
	private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

	@Override
	public BasicPaginationQueryResponse<Project> getUserProjects(String user,
			BasicPaginationQueryRequest queryRequest) {
		try {
			List<String> userRoles = iUserRoleService.getAllUserRoles(user);

			BasicPaginationQueryResponse<Project> userProjects = projectRepository.getUserProjects(user, queryRequest,
					userRoles);

			for (Project p : userProjects.getResultList()) {
				if (user.equals(p.getUserId())) {
					p.setProjectAccessType(EnumProjectAccessType.OWNER);
				} else if (userRoles.contains(UserRoleService.PROJECT_ROLE_PREFIX.concat(p.getName().toUpperCase()))) {
					p.setProjectAccessType(EnumProjectAccessType.MEMBER);
				} else {
					p.setProjectAccessType(EnumProjectAccessType.BIOBANK);
				}
			}

			return userProjects;
		} catch (Exception e) {
			log.error("Error while getting user projects", e);
			throw new RuntimeException("Error while getting user projects list");
		}
	}

	@Override
	public void createProject(String userId, CreateProjectRequest projectrequest) {
		Project project = new Project();
		project.setName(projectrequest.getName());
		project.setDescription(projectrequest.getDescription());
		project.setSampleType(projectrequest.getSampleType());

		if (project.getName() == null || project.getName().isEmpty()) {
			throw new RuntimeException("Project name cannot be empty");
		}

		if (project.getName().length() > MAX_PROJECT_NAME_LENGTH || project.getName().contains("_")
				|| project.getName().length() < MIN_PROJECT_NAME_LENGTH) {
			throw new BadRequestException("Project name cannot be longer than " + MAX_PROJECT_NAME_LENGTH
					+ " signs and contains underscores");
		}

		project.setStatus(EnumProjectStatus.NEW);
		project.setUserId(userId);
		try {
			if (projectRepository.existsProjectWithName(project.getName())) {
				throw new BadRequestException("Project with given name already exists");
			}
			String projectRole = iUserRoleService.createProjectRoles(project);
			project.setRolesIncluded(Arrays.asList(projectRole));
			projectRepository.save(project);

		} catch (RuntimeException e) {
			log.error("Error while creating project", e);
			throw e;
		} catch (Exception e) {
			log.error("Error while creating project", e);
			throw new RuntimeException("Erorr while creating project");
		}

	}

	@Override
	public void updateProject(String userId, UpdateProjectRequest updateProjectRequest) {
		Project orgProject = checkProjectAuthorization(userId, updateProjectRequest.getId(), true);
		checkModificationPossibility(orgProject);

		try {
			orgProject.setDescription(updateProjectRequest.getDescription());
			orgProject.setSampleType(updateProjectRequest.getSampleType());

			projectRepository.save(orgProject);
		} catch (Exception e) {
			log.error("Error while uploading project", e);
			throw new RuntimeException("Error while updating project");
		}

	}

	@Override
	public void deleteProject(String userId, String projectId) {
		Project project = checkProjectAuthorization(userId, projectId, true);
		checkModificationPossibility(project);

		try {
			projectRepository.deleteById(projectId);
			iUserRoleService.removeProjectRoles(project.getName());
		} catch (Exception e) {
			log.error("Delete project error", e);
			throw new RuntimeException("Error while deleteing project");
		}

	}

	@Override
	public List<Project> getNewUserOwnProjects(String userId) {
		return projectRepository.findByUserIdAndStatus(userId, EnumProjectStatus.NEW);
	}

	@Override
	public void addQueryToProject(String userId, ProjectQueryRequest projectQueryRequest) {
		Project project = checkProjectAuthorization(userId, projectQueryRequest.getProjectId(), true);
		checkModificationPossibility(project);
		try {

			if (projectQueryRequest.getQuery() == null || projectQueryRequest.getQuery().trim().isEmpty()
					|| projectQueryRequest.getQuery().trim().equals("*:*")) {
				throw new BadRequestException("Query cannot be empty");
			}

			if (projectRepository.queryExistsInProject(projectQueryRequest.getProjectId(),
					projectQueryRequest.getQuery())) {
				throw new BadRequestException("Given query already exists in project");
			}

			projectRepository.addQueryToProject(projectQueryRequest.getProjectId(), projectQueryRequest.getQuery());
		} catch (BadRequestException e) {
			throw e;
		} catch (Exception e) {
			log.error("error while adding query to project", e);
			throw new RuntimeException("Error while adding query to project");
		}
	}

	public void removeQueryFromProject(String userId, ProjectQueryRequest projectQueryRequest) {
		Project project = checkProjectAuthorization(userId, projectQueryRequest.getProjectId(), true);
		checkModificationPossibility(project);

		try {
			projectRepository.removeQueryFromProjects(projectQueryRequest.getProjectId(),
					projectQueryRequest.getQuery());
		} catch (Exception e) {
			log.error("Error while removing query from project", e);
			throw new RuntimeException("Error while removing query from project");
		}
	}

	@Override
	public Project checkProjectAuthorization(String userId, String projectId, boolean onlyInitiator) {
		try {
			if (projectId == null || projectId.isEmpty()) {
				throw new RuntimeException("project id cannot be empty");
			}

			Project project = projectRepository.findById(projectId).orElse(null);

			if (project == null) {
				throw new com.bioface.exception.BadRequestException("Project with given id doesn't exist");
			}

			if (onlyInitiator) {
				if (!userId.equals(project.getUserId())) {
					throw new AuthorizationException("Only project owner can modify or remove project");
				}
			} else {
				if (!userId.equals(project.getUserId())
						&& !iUserRoleService.hasAnyRole(userId, new ArrayList<String>(project.getRolesIncluded()))) {
					throw new AuthorizationException("You have no permission to this project");
				}
			}

			return project;
		} catch (AuthorizationException e) {
			log.error("Unauthorized request for project. ProjectId: " + projectId + " User: " + userId, e);
			throw e;
		} catch (BadRequestException bre) {
			throw bre;
		} catch (Exception e) {
			log.error("Error while checking project authorization", e);
			throw new RuntimeException("Error while checking project authorization");
		}

	}

	@Override
	public ProjectSampleQueryResponse executeProject(String user, ProjectSampleQueryRequest projectQueryRequest,
			boolean saveHistory) {

		Project project = checkProjectAuthorization(user, projectQueryRequest.getProjectId(), false);

		try {
			ProjectSampleQueryResponse response = new ProjectSampleQueryResponse();
			if (project.getQueries() == null || project.getQueries().isEmpty()) {
				throw new BadRequestException("Project cannot be empty");
			}

			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setStart(projectQueryRequest.getPage() * projectQueryRequest.getMaxRows());
			solrQuery.setRows(projectQueryRequest.getMaxRows());
			if (projectQueryRequest.getSortField() != null && !projectQueryRequest.getSortField().isEmpty()) {
				solrQuery.addSort(projectQueryRequest.getSortField(),
						projectQueryRequest.isSortDesc() ? ORDER.desc : ORDER.asc);
			}
			SolrDocumentList solrDocumentList = null;

			String userQuery = Utils.processSolrQueries(project.getQueries());
			String accessQuery = getAccessSolrQueryProjectPart(user, project, project.getBiobankShared());

			String queryWithAccess = userQuery;

			if (!accessQuery.isEmpty()) {
				queryWithAccess = "(" + queryWithAccess + ") " + "AND (" + accessQuery + ")";
			}
			solrQuery.setQuery(queryWithAccess);

			QueryResponse queryResponse = null;

			try {
				queryResponse = solrClient.query(solrQuery);
				solrDocumentList = queryResponse.getResults();

				long dispCount = countProjectSamplesFromDispersion(user, projectQueryRequest);
				
				response.setSamples(Utils.parseSolrResult(solrDocumentList).toString());

				if (saveHistory) {
					SolrQuery pivotQuery = new SolrQuery();
					pivotQuery.setQuery(queryWithAccess);
					pivotQuery.setFacet(true);
					pivotQuery.addFacetPivotField(
							SampleService.INSTITUTION_CI_FIELD_NAME + "," + SampleService.ACCESS_FIELD);

					registerProjectExecution(project, solrClient.query(pivotQuery), dispCount);
					response.setProject(projectRepository.findById(project.getId()).get());
				}

			} catch (SolrServerException e) {
				throw new RuntimeException("Query Exception");
			} catch (JSONException e) {
				throw new RuntimeException("Error while parsing JSON");
			} catch (Exception e) {
				throw new RuntimeException("Unknown error");
			}

			return response;

		} catch (BadRequestException bre) {
			throw bre;
		} catch (Exception e) {
			log.error("Error while executing project", e);
			throw new RuntimeException("Error while executing project");
		}
	}
	
	@Override
	public List<SearchInDispersionQueryResponse> executeProjectWithDispersion(String user, ProjectSampleQueryRequest projectQueryRequest) {
		
		Project project = checkProjectAuthorization(user, projectQueryRequest.getProjectId(), false);
		
		BasicPaginationQueryRequest queryRequest = new BasicPaginationQueryRequest();
		
		queryRequest.setQuery("(" + Utils.processSolrQueries(project.getQueries()) + ")");
		queryRequest.setMaxRows(5);
		queryRequest.setPage(projectQueryRequest.getPage());
		
		return iSampleService.getDispersionSamplesForQuery(queryRequest);
	}
	
	@Override
	public Long countProjectSamplesFromDispersion(String user, ProjectSampleQueryRequest projectQueryRequest) {
		
		Project project = checkProjectAuthorization(user, projectQueryRequest.getProjectId(), false);
		
		BasicPaginationQueryRequest queryRequest = new BasicPaginationQueryRequest();
		
		queryRequest.setQuery("(" + Utils.processSolrQueries(project.getQueries()) + ")");
		queryRequest.setMaxRows(1);
		queryRequest.setPage(projectQueryRequest.getPage());
		
		return iSampleService.countDispersionSamplesForQuery(queryRequest);
	}
	
	
	@Override
	public Project registerProjectExecution(Project project, QueryResponse queryResponse, long dispLength) {

		try {
			List<SampleResult> sampleResults = new ArrayList<>();

			queryResponse.getFacetPivot().forEach(fp -> {
				fp.getValue().forEach(fv -> {
					SampleResult sampleResult = new SampleResult();
					sampleResult.setBiobank(fv.getValue().toString());
					Map<EnumAccess, Integer> accessCountMap = new HashMap<>();
					fv.getPivot().forEach(pv -> {
						accessCountMap.put(EnumAccess.fromValue(pv.getValue().toString()), pv.getCount());
					});
					sampleResult.setResultMap(accessCountMap);
					sampleResults.add(sampleResult);
				});
			});

			sampleResults.forEach(res -> {
				if (res.getResultMap().size() != EnumAccess.values().length) {
					Arrays.stream(EnumAccess.values()).forEach(access -> {
						if (res.getResultMap().get(access) == null) {
							res.getResultMap().put(access, 0);
						}
					});

				}
			});

			ProjectHistory projectHistory = new ProjectHistory();
			projectHistory.setExecutedAt(new Date());
			projectHistory.setRowsNum(queryResponse.getResults().getNumFound());
			projectHistory.setRowsNumDisp(dispLength);
			projectHistory.setResult(sampleResults);

			projectRepository.updateProjectExecutionHistory(project, projectHistory);

			return project;

		} catch (Exception e) {
			log.error("Error while register project execution", e);
			throw new RuntimeException("Error while register project execution");
		}
	}

	@Override
	public void requestProject(String user, String projectId, String toDispersion) {
		Project project = checkProjectAuthorization(user, projectId, true);

		try {
			if (project.getStatus() != EnumProjectStatus.NEW) {
				throw new RuntimeException("Request can be sent only for project with new status.");
			}

			if (project.getQueries() == null || project.getQueries().isEmpty()) {
				throw new RuntimeException("Empty project cannot be requested");
			}

			ProjectSampleQueryRequest projectQueryRequest = new ProjectSampleQueryRequest();
			projectQueryRequest.setProjectId(projectId);
			projectQueryRequest.setPage(0);
			projectQueryRequest.setMaxRows(0);

			ProjectSampleQueryResponse queryResponse = this.executeProject(user, projectQueryRequest, true);
			ProjectHistory lastHistoryElement = Collections.max(queryResponse.getProject().getProjectHistory(),
					Comparator.comparing(h -> h.getExecutedAt()));

			List<String> biobankRolesIncluded = new ArrayList<>();

			lastHistoryElement.getResult().forEach(r -> {
				biobankRolesIncluded.add(UserRoleService.BIOBANK_ROLE_PREFIX + r.getBiobank().toUpperCase());
			});
			
			if(toDispersion.contains("true")) {
				List<SearchInDispersionQueryResponse> dispersionQueryResponse = executeProjectWithDispersion(user, projectQueryRequest);
				for(SearchInDispersionQueryResponse dispersion : dispersionQueryResponse) {
					if(!StringUtils.isEmpty(dispersion.getResult())) {
						biobankRolesIncluded.add(UserRoleService.BIOBANK_ROLE_PREFIX + dispersion.getName().toUpperCase() + UserRoleService.DISPERSION_EMPLOYEE_SUFFIX);
					}
				}
			}
			
			project = queryResponse.getProject();
			project.setStatus(EnumProjectStatus.REQUESTED);
			project.getRolesIncluded().addAll(biobankRolesIncluded);
			projectRepository.save(project);

			MessageChannel messageChannel = new MessageChannel();
			messageChannel.setChannelPropertyId(project.getId());
			messageChannel.setChannelPropertyType(EnumChannelPropertyType.project);
			List<String> accessGroups = new ArrayList<>();
			accessGroups
					.add(UserRoleService.PROJECT_ROLE_PREFIX + project.getName().replaceAll(" ", "_").toUpperCase());
			accessGroups.addAll(biobankRolesIncluded);
			messageChannel.setAccessGroups(accessGroups);

			iMessageChannelService.createMessageChannel(user, messageChannel);

			if (biobankRolesIncluded.size() > 1) {
				for (String bri : biobankRolesIncluded) {
					MessageChannel biobankMessageChannel = new MessageChannel();
					List<String> biobankMessageAccesGroups = new ArrayList<>();
					biobankMessageChannel.setChannelPropertyId(project.getId());
					biobankMessageChannel.setChannelPropertyType(EnumChannelPropertyType.project);
					biobankMessageChannel.setBiobank(bri.replaceFirst(UserRoleService.BIOBANK_ROLE_PREFIX, "").replaceFirst(UserRoleService.DISPERSION_EMPLOYEE_SUFFIX, ""));
					biobankMessageAccesGroups.add(
							UserRoleService.PROJECT_ROLE_PREFIX + project.getName().replaceAll(" ", "_").toUpperCase());
					biobankMessageAccesGroups.add(bri);
					biobankMessageChannel.setAccessGroups(biobankMessageAccesGroups);
					iMessageChannelService.createMessageChannel(user, biobankMessageChannel);
				}
			}
		} catch (Exception e) {
			log.error("Error while requesting project", e);
			throw new RuntimeException("Error while requesting project");
		}

	}

	@Override
	public void closeProject(String user, String projectId) {
		Project project = checkProjectAuthorization(user, projectId, true);

		if (project.getStatus() != EnumProjectStatus.REQUESTED) {
			throw new BadRequestException("Only sended project can be closed");
		}
		try {
			project.setStatus(EnumProjectStatus.CLOSED);
			projectRepository.save(project);
		} catch (Exception e) {
			log.error("Error while closing project", e);
			throw e;
		}
	}

	private void checkModificationPossibility(Project project) {
		if (EnumProjectStatus.NEW != project.getStatus()) {
			throw new RuntimeException("Modification forbidden for project in this status");
		}

	}

	@Override
	public void shareProject(String user, ShareProjectRequest request) {
		Project project = checkProjectAuthorization(user, request.getProjectId(), true);

		try {
			if (EnumProjectStatus.NEW != project.getStatus()) {
				throw new RuntimeException("Sharing project possible only for new project");
			}
			log.info("User " + user + " shared project with id:" + request.getProjectId() + " for user "
					+ request.getUsername());
			if (user.equals(request.getUsername())) {
				throw new RuntimeException("You can't share project for owner");
			}

			if (!iUserRoleService.userExists(request.getUsername())) {
				throw new RuntimeException("User" + request.getUsername() + "not exists in database");
			}
			iUserRoleService.addProjectUserRole(project, request.getUsername());
		} catch (Exception e) {
			log.error("Error while sharing project", e);
			throw new RuntimeException("Error while sharing project");
		}

	}

	private String getAccessSolrQueryProjectPart(String user, Project project, List<String> accessGroups) {
		StringBuilder accessQueryBuilder = new StringBuilder();
		accessQueryBuilder.append(SampleService.ACCESS_FIELD + ":").append(EnumAccess.ACCESSIBLE.getValue());

		if (accessGroups != null) {
			List<String> protectedBiobankAccessRoles = accessGroups.stream()
					.filter(b -> b.endsWith(UserRoleService.BIOBANK_PROTECTED_SUFFIX)).collect(Collectors.toList());

			if (protectedBiobankAccessRoles.isEmpty()) {
				return accessQueryBuilder.toString();
			}

			List<String> finalUserRolesList = new ArrayList<>();

			if (SecurityUtils.getSubject()
					.hasRole(UserRoleService.PROJECT_ROLE_PREFIX + project.getName().toUpperCase())
					|| user.equals(project.getUserId())) {
				finalUserRolesList.addAll(protectedBiobankAccessRoles);
			} else {
				List<String> userBiobankRoles = iUserRoleService.getRolesWithPrefixForUser(user,
						UserRoleService.BIOBANK_ROLE_PREFIX);

				userBiobankRoles.forEach(ubr -> {
					protectedBiobankAccessRoles.forEach(ar -> {
						if (ubr.equals(ar) || ubr.concat(UserRoleService.BIOBANK_PROTECTED_SUFFIX).equals(ar)) {
							finalUserRolesList.add(ar);
						}
					});
				});
			}

			accessQueryBuilder.append(" OR (" + SampleService.INSTITUTION_CI_FIELD_NAME + ":(");
			IntStream.range(0, finalUserRolesList.size()).forEach(i -> {
				finalUserRolesList.set(i,
						finalUserRolesList.get(i).replaceFirst(UserRoleService.BIOBANK_ROLE_PREFIX, "")
								.replaceFirst(UserRoleService.BIOBANK_PROTECTED_SUFFIX, ""));
			});
			accessQueryBuilder.append(String.join(" OR ", finalUserRolesList));
			accessQueryBuilder.append(") AND " + SampleService.ACCESS_FIELD + ":")
					.append(EnumAccess.LIMITED_ACCESS.getValue()).append(")");

		}
		return accessQueryBuilder.toString();
	}

	@Override
	public List<String> fromBiobankList(String user, String projectId) {
		Project project = checkProjectAuthorization(user, projectId, false);

		try {
			List<String> userRoles = iUserRoleService.getRolesWithPrefixForUser(user,
					UserRoleService.BIOBANK_ROLE_PREFIX);

			userRoles.retainAll(project.getRolesIncluded());

			List<String> biobankListToShare = new ArrayList<>();

			if (project.getBiobankShared() != null) {
				userRoles.stream().forEach(ur -> {
					if (!project.getBiobankShared().stream()
							.anyMatch(bs -> bs.equals(ur + UserRoleService.BIOBANK_PROTECTED_SUFFIX))) {
						biobankListToShare.add(ur.replaceFirst(UserRoleService.BIOBANK_ROLE_PREFIX, ""));
					}
				});
			} else {
				userRoles.stream().forEach(ur -> {
					biobankListToShare.add(ur.replaceFirst(UserRoleService.BIOBANK_ROLE_PREFIX, ""));
				});

			}

			return biobankListToShare;
		} catch (Exception e) {
			log.error("Error while getting from which biobanks is user", e);
			throw new RuntimeException("Error while getting information about user biobanks");
		}
	}

	@Override
	public void shareSamplesForProject(String user, ShareSamplesToProjectRequest request) {
		if (request.getProjectId() == null || request.getProjectId().isEmpty() || request.getBiobank() == null
				|| request.getBiobank().isEmpty()) {
			throw new BadRequestException("Biobank and id of project cannot be empty");
		}

		Project project = checkProjectAuthorization(user, request.getProjectId(), false);

		try {
			String biobankRole = UserRoleService.BIOBANK_ROLE_PREFIX + request.getBiobank().toUpperCase();

			if (!SecurityUtils.getSubject().hasRole(biobankRole)) {
				throw new RuntimeException("You have no permission to share samples from this biobank");
			}

			String biobankRoleProtected = biobankRole + UserRoleService.BIOBANK_PROTECTED_SUFFIX;

			if (project.getBiobankShared() != null
					&& project.getBiobankShared().stream().anyMatch(bs -> bs.equals(biobankRoleProtected))) {
				throw new BadRequestException("Samples already shared");
			}

			projectRepository.addSharedBiobank(request.getProjectId(), biobankRoleProtected);
			log.info("User " + user + " shared samples from biobank: " + request.getBiobank() + " for project with id "
					+ request.getProjectId());
		} catch (BadRequestException bre) {
			throw bre;
		} catch (Exception e) {
			log.error("Error while sharing samples for project", e);
			throw e;
		}

	}

	@Override
	public List<String> getProjectUsers(String user, String projectId) {
		Project project = checkProjectAuthorization(user, projectId, false);

		try {
			List<String> response = iUserRoleService
					.getUsersWithRole(UserRoleService.PROJECT_ROLE_PREFIX + project.getName().toUpperCase());
			response.add(project.getUserId());
			return response;

		} catch (Exception e) {
			log.error("Error while getting project users", e);
			throw new RuntimeException("Error while getting user's projects");
		}
	}

}
