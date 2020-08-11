package com.bioface.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bee2code.security.core.authorization.realm.MainJdbcRealm;
import com.bioface.exception.BadRequestException;
import com.bioface.model.Biobank;
import com.bioface.model.DispersionInstance;
import com.bioface.model.Project;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.model.ext.GetUsersManageResponse;
import com.bioface.model.ext.NewUserRequest;
import com.bioface.model.ext.UserRolesResponse;

@Service
public class UserRoleService implements IUserRoleService {

	@Autowired
	private MainJdbcRealm mainJdbcRealm;

	@Autowired
	private DataSource dataSource;

	private static final Logger log = LoggerFactory.getLogger(UserRoleService.class);

	@Value("${keycloak.auth-server-url}")
	private String keycloakUrl;

	@Value("${keycloak.realm}")
	private String keycloakRealm;

	@Value("${admin.keycloak.username}")
	private String adminUsername;

	@Value("${admin.keycloak.password}")
	private String adminPassword;

	@Value("${admin.keycloak.client}")
	private String adminClient;

	public static final String BIOBANK_ROLE_PREFIX = "BIOBANK_";
	public static final String BIOBANK_PROTECTED_SUFFIX = "_PROTECTED";
	public static final String PROJECT_ROLE_PREFIX = "PROJECT_";
	public static final String BIOBANK_PERMISSION_PREFIX = "biobank:";
	public static final String BIOBANK_PERMISSION_EDIT_SUFFIX = ":edit";
	public static final String BIOBANK_PERMISSION_IMPORT_SUFFIX = ":import";
	public static final String DISPERSION_EMPLOYEE_SUFFIX = "_EMPLOYEE";

	private static final int DUPLICATE_PRIMARY_KEY_ERROR_CODE = 23001;

	@Override
	public void createBiobankRoles(Biobank biobank) {
		try {

			Set<Permission> permissions = new HashSet<>();
			String biobankEditPermission = BIOBANK_PERMISSION_PREFIX + biobank.getBiobankId().toUpperCase()
					+ BIOBANK_PERMISSION_EDIT_SUFFIX;
			String biobankImportPermission = BIOBANK_PERMISSION_PREFIX + biobank.getBiobankId().toUpperCase()
					+ BIOBANK_PERMISSION_IMPORT_SUFFIX;
			permissions.add(new WildcardPermission(biobankEditPermission));
			permissions.add(new WildcardPermission(biobankImportPermission));
			String biobankMainRole = BIOBANK_ROLE_PREFIX + biobank.getBiobankId().toUpperCase();
			String biobankProtectedRole = BIOBANK_ROLE_PREFIX + biobank.getBiobankId().toUpperCase()
					+ BIOBANK_PROTECTED_SUFFIX;
			mainJdbcRealm.addRoleAndPermissions(biobankMainRole, permissions);
			mainJdbcRealm.addRole(biobankProtectedRole);

			log.info("Roles created: " + biobankMainRole + ", " + biobankProtectedRole);
			log.info("Permissions created: " + biobankEditPermission + ", " + biobankImportPermission);
		} catch (SQLException e) {
			log.error("Error while creating biobank roles", e);
			throw new RuntimeException("Error while creating biobank roles");
		}
	}
	
	@Override
	public void createDispersionInstanceRoles(DispersionInstance dispersionInstance) {
		try {

			Set<Permission> permissions = new HashSet<>();
			String dispersionInstanceEmployeeRole = BIOBANK_ROLE_PREFIX + dispersionInstance.getName().toUpperCase()
					+ DISPERSION_EMPLOYEE_SUFFIX;
			permissions.add(new WildcardPermission(dispersionInstanceEmployeeRole));
			mainJdbcRealm.addRole(dispersionInstanceEmployeeRole);
		} catch (SQLException e) {
			log.error("Error while creating dispersion biobank roles", e);
			throw new RuntimeException("Error while creating dispersion biobank roles");
		}
	}

	@Override
	public String createProjectRoles(Project project) {
		try {
			String roleName = PROJECT_ROLE_PREFIX + project.getName().replaceAll(" ", "_").toUpperCase();
			mainJdbcRealm.addRole(roleName);
			log.info("Role created: " + roleName);
			return roleName;
		} catch (SQLException e) {
			log.error("Error while creating project roles", e);
			throw new RuntimeException("Error while creating project roles");
		}
	}

	@Override
	public void addProjectUserRole(Project project, String username) {
		try {
			mainJdbcRealm.addUserToRole(username,
					PROJECT_ROLE_PREFIX + project.getName().replaceAll(" ", "_").toUpperCase());
			log.info("User: " + username + " added to project: " + project.getName() + "(" + project.getId() + ")");
		} catch (SQLException e) {
			if (e.getErrorCode() == DUPLICATE_PRIMARY_KEY_ERROR_CODE) {
				throw new BadRequestException("Selected user already has access to this project");
			}
			log.error("Error while creating project roles", e);
			throw new RuntimeException("Error while creating project roles");
		} catch (Exception e) {
			log.error("Error while creating project roles", e);
			throw new RuntimeException("Error while creating project roles");
		}
	}

	@Override
	public List<String> getAllUserRoles(String username) {
		try {
			String sql = "SELECT r.role_name FROM ROLES r INNER JOIN USER_ROLES ur ON ur.role_name = r.role_name "
					+ "WHERE ur.username = ?";
			Connection connection = dataSource.getConnection();
			List<String> roles = new ArrayList<>();

			PreparedStatement getUserRolesStatement = connection.prepareStatement(sql);
			getUserRolesStatement.setString(1, username);

			ResultSet result = getUserRolesStatement.executeQuery();

			while (result.next()) {
				roles.add(result.getString("role_name"));
			}

			connection.close();

			return roles;

		} catch (Exception e) {
			log.error("Error while getting all user roles for user: " + username, e);
			throw new RuntimeException("Error while getting user roles");
		}

	}

	@Override
	public List<String> getRolesWithPrefixForUser(String username, String prefix) {

		try {
			String sql = "SELECT r.role_name FROM ROLES r " + "INNER JOIN USER_ROLES ur ON ur.role_name = r.role_name "
					+ "WHERE ur.username = '" + username + "' AND r.role_name LIKE '" + prefix + "%';";
			Connection connection = dataSource.getConnection();
			List<String> biobankRoles = new ArrayList<>();
			ResultSet result = connection.createStatement().executeQuery(sql);

			while (result.next()) {
				biobankRoles.add(result.getString("role_name"));
			}

			connection.close();

			return biobankRoles;

		} catch (Exception e) {
			log.error("Error while geting roles with prefix (user: " + username + ", prefix:" + prefix + ")", e);
			throw new RuntimeException("Error while geting roles with prefix");
		}

	}

	@Override
	public BasicPaginationQueryResponse<String> getAllUsers() {
		try {
			String sql = "SELECT username FROM USERS;";
			Connection connection = dataSource.getConnection();
			List<String> users = new ArrayList<>();
			ResultSet result = connection.createStatement().executeQuery(sql);

			while (result.next()) {
				users.add(result.getString("username"));
			}

			connection.close();

			BasicPaginationQueryResponse<String> response = new BasicPaginationQueryResponse<>();
			response.setResultList(users);
			return response;
		} catch (SQLException e) {
			log.error("Error while getting all users list", e);
			throw new RuntimeException("Error while getting all users list");
		}
	}

	@Override
	public List<String> getPotencialUserRoles(String username) {
		try {
			String sql = "SELECT r.role_name FROM ROLES r WHERE NOT EXISTS (Select role_name FROM USER_ROLES WHERE role_name = r.role_name and username = ?) ";
			Connection connection = dataSource.getConnection();
			List<String> roles = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = connection.createStatement().executeQuery(sql);

			while (result.next()) {
				roles.add(result.getString("role_name"));
			}

			connection.close();

			return roles;
		} catch (Exception e) {
			log.error("Error while getting all users list", e);
			throw new RuntimeException("Error while getting all users list");
		}
	}

	@Override
	public void addUserRole(String username, String role) {
		try {
			mainJdbcRealm.addUserToRole(username, role);
			log.info("Added role: " + role + " for user: " + username);
		} catch (SQLException e) {
			if (e.getErrorCode() == DUPLICATE_PRIMARY_KEY_ERROR_CODE) {
				throw new RuntimeException("User already has selected role");
			}
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void removeUserRole(String username, String role) {
		try {
			String sql = "DELETE FROM USER_ROLES WHERE username = ? AND role_name = ?";
			Connection connection = dataSource.getConnection();

			PreparedStatement deleteUserRoleStatement = connection.prepareStatement(sql);
			deleteUserRoleStatement.setString(1, username);
			deleteUserRoleStatement.setString(2, role);

			deleteUserRoleStatement.executeUpdate();

			log.info("User role removed (user:" + username + " role: " + role + ")");
			connection.close();
		} catch (Exception e) {
			log.error("Error while removing userrole", e);
			throw new RuntimeException("Error while removing user role");
		}
	}

	@Override
	public void addKeycloakUser(NewUserRequest request) {
		try {
			Keycloak kc = KeycloakBuilder.builder().serverUrl(keycloakUrl).realm(keycloakRealm).username(adminUsername)
					.password(adminPassword).clientId(adminClient).build();

			UserRepresentation ur = new UserRepresentation();
			ur.setUsername(request.getUsername());
			ur.setRequiredActions(Arrays.asList("UPDATE_PASSWORD"));
			ur.setEmail(request.getEmail());
			ur.setEmailVerified(true);
			ur.setEnabled(true);
			UsersResource userResource = kc.realm(keycloakRealm).users();
			Response response = userResource.create(ur);

			if (response.getStatus() == 409) {
				throw new RuntimeException("User with given username already exists");
			}

			String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

			org.keycloak.admin.client.resource.UserResource res = userResource.get(userId);
			res.sendVerifyEmail();

			log.info("User added to keycloak (username: " + request.getUsername() + " email: " + request.getEmail()
					+ ")");

		} catch (Exception e) {
			log.error("Adding new user - undefined error", e);
			throw new RuntimeException("Error while adding new user");
		}

	}

	@Override
	public boolean hasAnyRole(String username, List<String> roles) {
		try {

			if (roles != null && !roles.isEmpty()) {
				IntStream.range(0, roles.size()).forEach(i -> {
					roles.set(i, "'" + roles.get(i) + "'");
				});
				String sql = "SELECT COUNT(*) FROM USER_ROLES WHERE role_name IN (" + String.join(",", roles)
						+ ") AND username = '" + username + "';";

				Connection connection = dataSource.getConnection();

				boolean result = connection.createStatement().executeQuery(sql).first();
				connection.close();
				return result;
			}

			return false;
		} catch (SQLException e) {
			log.error("Error while checking user roles", e);
			throw new RuntimeException("Error while checking user roles");
		}
	}

	@Override
	public UserRolesResponse getBasicUserRoles(String username) {
		try {
			UserRolesResponse response = new UserRolesResponse();
			if (SecurityUtils.getSubject().hasRole("ADMIN")) {
				response.setAdmin(true);
			}

			List<String> biobankUserRoles = getRolesWithPrefixForUser(username, UserRoleService.BIOBANK_ROLE_PREFIX);
			Predicate<String> biobankAdminCondition = b -> !b.endsWith(UserRoleService.BIOBANK_PROTECTED_SUFFIX) && !b.endsWith(UserRoleService.DISPERSION_EMPLOYEE_SUFFIX);

			response.setBiobank(biobankUserRoles.stream().anyMatch(biobankAdminCondition));

			return response;
		} catch (Exception e) {
			log.error("Error while checking user basic roles");
			throw new RuntimeException("Error while checking user basic roles");
		}
	}

	@Override
	public void removeProjectRoles(String projectName) {
		try {
			String projectRole = PROJECT_ROLE_PREFIX + projectName.replaceAll(" ", "_").toUpperCase();
			String removeUserRolesSql = "DELETE FROM USER_ROLES WHERE role_name = ?";
			String removeRoleSql = "DELETE FROM ROLES WHERE role_name = ?";

			Connection connection = dataSource.getConnection();

			connection.setAutoCommit(false);

			PreparedStatement removeUserRolesStatement = connection.prepareStatement(removeUserRolesSql);
			PreparedStatement removeRoleStatement = connection.prepareStatement(removeRoleSql);

			removeUserRolesStatement.setString(1, projectRole);
			removeRoleStatement.setString(1, projectRole);

			removeUserRolesStatement.executeUpdate();
			removeRoleStatement.executeUpdate();

			log.info("Remove role and userroles for project named: " + projectName);

			connection.commit();

		} catch (SQLException e) {
			log.error("Error while removing project role and user roles", e);
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void addUserIfNotExists(String username, String oauthId) {

		try {
			String selectSql = "SELECT username FROM USERS WHERE username = ?";

			Connection connection = dataSource.getConnection();
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);
			selectStatement.setString(1, username);

			if (selectStatement.executeQuery().first()) {
				connection.close();
				return;
			} else {
				String createUserSql = "INSERT INTO USERS(USERNAME, OAUTHID) VALUES (?,?)";
				PreparedStatement createUserStatement = connection.prepareStatement(createUserSql);
				createUserStatement.setString(1, username);
				createUserStatement.setString(2, oauthId);
				createUserStatement.executeUpdate();
				connection.close();
			}
		} catch (SQLException e) {
			log.error("SQL error while creating user", e);
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			log.error("Unknown error while creating user", e);
			throw e;
		}
	}

	@Override
	public List<String> getUsersWithRole(String role) {
		try {
			String sql = "SELECT username FROM USER_ROLES WHERE role_name = ?";
			Connection connection = dataSource.getConnection();

			PreparedStatement selectStatement = connection.prepareStatement(sql);
			selectStatement.setString(1, role);

			List<String> response = new ArrayList<>();

			ResultSet resultSet = selectStatement.executeQuery();

			while (resultSet.next()) {
				response.add(resultSet.getString("username"));
			}
			connection.close();
			return response;

		} catch (SQLException e) {
			log.error("Sql error while getting users for role", e);
			throw new RuntimeException("Error while getting users with role");
		} catch (Exception e) {
			log.error("Undefined error while getting users for role", e);
			throw new RuntimeException("Error while getting users with role");
		}
	}

	@Override
	public void changeUserAvailability(String username, boolean enabled) {

		try {
			Keycloak kc = KeycloakBuilder.builder().serverUrl(keycloakUrl).realm(keycloakRealm).username(adminUsername)
					.password(adminPassword).clientId(adminClient).build();

			String getUserOauthIdSql = "SELECT OAUTHID FROM USERS WHERE USERNAME = ?";
			Connection connection = dataSource.getConnection();
			PreparedStatement getOauthIdStatement = connection.prepareStatement(getUserOauthIdSql);
			getOauthIdStatement.setString(1, username);
			ResultSet userResult = getOauthIdStatement.executeQuery();

			String oauthId;
			if (userResult.next()) {
				oauthId = userResult.getString("OAUTHID");
			} else {
				log.error("User with username " + username + "doesn't exists");
				throw new RuntimeException(
						"Error while disabling user. User doesn't exists (username: " + username + ")");
			}

			UserResource resource = kc.realm(keycloakRealm).users().get(oauthId);
			UserRepresentation representation = resource.toRepresentation();
			representation.setEnabled(enabled);
			resource.update(representation);

			log.info("Changed user with username: " + username + " enable to: " + enabled);
		} catch (Exception e) {
			log.error("Error while disabling user", e);
			throw new RuntimeException("Error while disabling user");
		}
	}

	@Override
	public List<GetUsersManageResponse> getUsersList() {
		Keycloak kc = KeycloakBuilder.builder().serverUrl(keycloakUrl).realm(keycloakRealm).username(adminUsername)
				.password(adminPassword).clientId(adminClient).build();
		List<GetUsersManageResponse> usersList = new ArrayList<>();
		kc.realm(keycloakRealm).users().list().forEach(ur -> {
			usersList.add(new GetUsersManageResponse(ur.getUsername(), ur.isEnabled()));
		});

		return usersList;
	}

	@Override
	public boolean userExists(String username) {
		try {
			String selectSql = "SELECT username FROM USERS WHERE username = ?";

			Connection connection = dataSource.getConnection();
			PreparedStatement selectStatement = connection.prepareStatement(selectSql);

			selectStatement.setString(1, username);

			boolean exists = selectStatement.executeQuery().first();

			return exists;
		} catch (SQLException e) {
			log.error("Error while checking if user exists", e);
			throw new RuntimeException("Error while checking if user exists");
		}

	}

}
