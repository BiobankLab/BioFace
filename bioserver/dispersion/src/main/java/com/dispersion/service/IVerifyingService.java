package com.dispersion.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface IVerifyingService {

	String verifyToken(DecodedJWT decodedToken);

	Boolean verifyAdminToken(DecodedJWT decodedToken);
	
}