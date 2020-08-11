package com.dispersion.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dispersion.service.VerifyingService;

public class JwtTokenFilter extends OncePerRequestFilter {

	private VerifyingService verifyingService;

	private static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	public JwtTokenFilter(VerifyingService verifyingService) {
		this.verifyingService = verifyingService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		String token = req.getHeader(SecurityConstans.HEADER_STRING);

		if (token == null || !token.startsWith(SecurityConstans.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
		} else {
			SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
			chain.doFilter(req, res);
		}

	}

	private Authentication getAuthentication(String token) {
		try {
			
			DecodedJWT decodedToken = JWT.decode(token.replace(SecurityConstans.TOKEN_PREFIX, ""));
			if(!decodedToken.getClaim("admin").isNull()) {
				if(decodedToken.getExpiresAt().compareTo(DateTime.now().toDate()) < 0){
					return null;
				}
				verifyingService.verifyAdminToken(decodedToken);
				return new UsernamePasswordAuthenticationToken("admin", null, new ArrayList<>());
			} else {
				
				String institution = verifyingService.verifyToken(decodedToken);
				if (!StringUtils.isEmpty(institution)) {
					return new UsernamePasswordAuthenticationToken(institution, null, new ArrayList<>());
				}
			}
		} catch (Exception e) {
			log.error("Error while authentication ", e);
		}
		
		return null;
	}

}