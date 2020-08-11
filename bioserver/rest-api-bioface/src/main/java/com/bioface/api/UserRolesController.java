package com.bioface.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bioface.model.ext.UserRolesResponse;
import com.bioface.service.IUserRoleService;

@Controller
@RequestMapping("/userroles")
public class UserRolesController {

	@Autowired
	private IUserRoleService iUserRolesService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<UserRolesResponse> getBasicUserRoles(Principal principal) {
		return new ResponseEntity<UserRolesResponse>(iUserRolesService.getBasicUserRoles(principal.getName()),
				HttpStatus.OK);
	}
}
