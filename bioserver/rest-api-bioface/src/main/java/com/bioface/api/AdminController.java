package com.bioface.api;

import java.security.Principal;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bioface.model.DispersionInstance;
import com.bioface.model.ext.GetUsersManageResponse;
import com.bioface.model.ext.NewUserRequest;
import com.bioface.model.ext.UserManageRequest;
import com.bioface.model.ext.UserRoleRequest;
import com.bioface.service.IDispersionInstanceService;
import com.bioface.service.IUserRoleService;

@Controller
@RequestMapping(value = "/admin")
@RequiresRoles("ADMIN")
public class AdminController {

	@Autowired
	private IUserRoleService iUserRoleService;
	
	@Autowired
	private IDispersionInstanceService iDispersionInstanceService;

	@RequestMapping(method = RequestMethod.GET, value = "/userRoles/{username}")
	public ResponseEntity<List<String>> getUserRoles(Principal principal,
			@PathVariable String username) {
		return new ResponseEntity<>(iUserRoleService.getAllUserRoles(username),
				HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/potencialUserRoles/{username}")
	public ResponseEntity<List<String>> getPotencialUserRoles(Principal principal,
			@PathVariable String username) {
		return new ResponseEntity<>(iUserRoleService.getPotencialUserRoles(username),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/userrole")
	public ResponseEntity<Void> addUserRole(Principal principal, @RequestBody UserRoleRequest request) {
		iUserRoleService.addUserRole(request.getUser(), request.getRole());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteUserRole")
	public ResponseEntity<Void> deleteUserRole(Principal principal, @RequestBody UserRoleRequest request) {
		iUserRoleService.removeUserRole(request.getUser(), request.getRole());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/newUser")
	public ResponseEntity<Void> addUser(Principal principal, @RequestBody NewUserRequest request) {
		iUserRoleService.addKeycloakUser(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public ResponseEntity<List<GetUsersManageResponse>> getEnabledUsersList(Principal principal) {
		return new ResponseEntity<>(iUserRoleService.getUsersList(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/disableUser")
	public ResponseEntity<Void> disableUser(Principal principal, @RequestBody UserManageRequest userManageRequest) {
		iUserRoleService.changeUserAvailability(userManageRequest.getUsername(), userManageRequest.isToEnable());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/dispersion")
	public ResponseEntity<Void> addDispersionInstance(@RequestBody DispersionInstance dispersionInstance) {
		iDispersionInstanceService.saveDispersionInstance(dispersionInstance);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/dispersion/{instanceId}")
	public ResponseEntity<Void> removeDispersionInstance(@PathVariable("instanceId") String id) {
		iDispersionInstanceService.removeDispersionInstance(id);
		return ResponseEntity.ok().build();
	}
}
