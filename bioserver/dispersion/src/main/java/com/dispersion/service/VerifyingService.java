package com.dispersion.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class VerifyingService implements IVerifyingService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Environment environment;

	private static final Logger log = LoggerFactory.getLogger(VerifyingService.class);

	@Override
	public String verifyToken(DecodedJWT decodedToken) {
		try {

			Claim institutionNameClaim = decodedToken.getClaim("institution");

			String key = getPublicKey(institutionNameClaim.asString());

			RSAPublicKey publicKey = (RSAPublicKey) convertPublicKey(key);

			Algorithm algorithmRS = Algorithm.RSA256(publicKey, null);

			algorithmRS.verify(decodedToken);

			return institutionNameClaim.asString();
		} catch (JWTDecodeException e) {
			log.error("Token invalid", e);
			throw new RuntimeException("Token invalid");
		} catch (SignatureVerificationException e) {
			log.error("Token's Signature is invalid", e);
			throw new RuntimeException("Token's Signature is invalid");
		} catch (Exception e) {
			log.error("Error occured while verifying token", e);
			throw new RuntimeException("Error occured while verifying token");
		}

	}

	@Override
	public Boolean verifyAdminToken(DecodedJWT decodedToken) {
		try {

			Algorithm algorithmRS = Algorithm.HMAC256(environment.getProperty("jwt.secret"));

			algorithmRS.verify(decodedToken);

			return true;
		} catch (JWTDecodeException e) {
			log.error("Token invalid", e);
			throw new RuntimeException("Token invalid");
		} catch (SignatureVerificationException e) {
			log.error("Token's Signature is invalid", e);
			throw new RuntimeException("Token's Signature is invalid");
		} catch (Exception e) {
			log.error("Error occured while verifying token", e);
			throw new RuntimeException("Error occured while verifying token");
		}

	}

	private String getPublicKey(String institution) {

		try(Connection connection = dataSource.getConnection();) {
			String key = null;
    		String sql = "SELECT NAME, \"KEY\" FROM PUBLIC.BIOBANK WHERE NAME = ?";

			PreparedStatement getInstitutionPublicKeyStatement = connection.prepareStatement(sql);
			getInstitutionPublicKeyStatement.setString(1, institution);

			ResultSet rs = getInstitutionPublicKeyStatement.executeQuery();

			while (rs.next()) {
				key = rs.getString("KEY");
            }

			return key;

    	} catch (SQLException e) {
    		log.error("Error occured while getting public key", e);
			throw new RuntimeException("Error occured while getting public key");
		} catch (Exception e) {
			log.error("Error occured while getting public key", e);
			throw new RuntimeException("Error occured while getting public key");
		}
	}

	private PublicKey convertPublicKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, URISyntaxException {
		try {
			key.replaceAll("\n", "");
			key = key.replace("-----BEGIN PUBLIC KEY-----", "")
					.replace("-----END PUBLIC KEY-----", "").replaceAll("\n", "").replaceAll("\r", "");
			byte[] decoded = Base64.decodeBase64(key);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(keySpec);

			return pubKey;
		} catch (Exception e) {
			log.error("Error occured while verifying token", e);
			throw new RuntimeException("Error occured while converting public key");
		}
    }

}
