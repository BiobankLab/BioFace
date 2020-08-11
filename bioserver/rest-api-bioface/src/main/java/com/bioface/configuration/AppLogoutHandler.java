package com.bioface.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.springsecurity.authentication.KeycloakLogoutHandler;
import org.springframework.security.core.Authentication;

public class AppLogoutHandler extends KeycloakLogoutHandler {

	
	public AppLogoutHandler(AdapterDeploymentContext adapterDeploymentContext) {
		super(adapterDeploymentContext);
		
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		super.logout(request, response, authentication);
		SecurityUtils.getSubject().logout();
	}

}
