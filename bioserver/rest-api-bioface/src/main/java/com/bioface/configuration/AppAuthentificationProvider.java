package com.bioface.configuration;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.bee2code.security.core.authentification.OpenIdToken;
import com.bioface.service.IUserRoleService;

public class AppAuthentificationProvider extends KeycloakAuthenticationProvider {

	private Realm realm;

	private IUserRoleService iUserRoleService;

	public AppAuthentificationProvider(Realm realm, IUserRoleService iUserRoleService) {
		super();
		this.realm = realm;
		this.iUserRoleService = iUserRoleService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) super.authenticate(authentication);
		ensureUserIsLoggedOut();
		Subject subject = SecurityUtils.getSubject();
		SimpleKeycloakAccount account = (SimpleKeycloakAccount) token.getDetails();
		String oauthId = account.getKeycloakSecurityContext().getToken().getId();
		String userId = token.getAccount().getKeycloakSecurityContext().getToken().getSubject();
		String username = token.getName();

		iUserRoleService.addUserIfNotExists(username, userId);

		OpenIdToken tokenId = new OpenIdToken(username, oauthId);
		subject.login(tokenId);
		return token;
	}

	private void ensureUserIsLoggedOut() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser == null)
				return;
			currentUser.logout();
			Session session = currentUser.getSession(false);
			if (session == null)
				return;
			session.stop();
		} catch (Exception e) {
		}
	}
}
