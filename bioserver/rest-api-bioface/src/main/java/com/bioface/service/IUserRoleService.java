package com.bioface.service;

import java.util.List;

import com.bioface.model.Biobank;
import com.bioface.model.DispersionInstance;
import com.bioface.model.Project;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.model.ext.GetUsersManageResponse;
import com.bioface.model.ext.NewUserRequest;
import com.bioface.model.ext.UserRolesResponse;

public interface IUserRoleService {

	void createBiobankRoles(Biobank biobank);

	String createProjectRoles(Project project);

	void addProjectUserRole(Project project, String username);

	List<String> getAllUserRoles(String user);

	List<String> getRolesWithPrefixForUser(String user, String prefix);

	BasicPaginationQueryResponse<String> getAllUsers();

	List<String> getPotencialUserRoles(String user);

	void addUserRole(String user, String role);

	void removeUserRole(String user, String role);

	void addKeycloakUser(NewUserRequest request);

	boolean hasAnyRole(String user, List<String> roles);

	UserRolesResponse getBasicUserRoles(String user);

	void removeProjectRoles(String projectName);

	void addUserIfNotExists(String username, String oauthId);

	List<String> getUsersWithRole(String role);

	void changeUserAvailability(String username, boolean enabled);

	List<GetUsersManageResponse> getUsersList();

	boolean userExists(String username);

	void createDispersionInstanceRoles(DispersionInstance dispersionInstance);

}
