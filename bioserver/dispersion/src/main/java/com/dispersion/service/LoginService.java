package com.dispersion.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dispersion.model.LoginData;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private Environment environment;
	
	private static final Logger log = LoggerFactory.getLogger(LoginService.class);
	
	/* (non-Javadoc)
	 * @see com.dispersion.service.ILoginService#checkLoginData(com.dispersion.model.LoginData)
	 */
	public String checkLoginData(LoginData loginData) throws SQLException {
		
		try(Connection connection = dataSource.getConnection()){
			
			String sql = "SELECT PASSWORD FROM LOGIN_DATA WHERE USERNAME = '" + loginData.getUsername() + "'";
			
			PreparedStatement getPasswordStatement = connection.prepareStatement(sql);
			
			ResultSet result = getPasswordStatement.executeQuery();
			
			String password = "";
			
			while (result.next()) {
				password = result.getString("PASSWORD");
			}
			
			if(BCrypt.checkpw(loginData.getPassword(), password)) {
				String token = createToken();
				
				return token;
			}
		} catch(Exception e) {
			log.error("Wrong login or password", e);
		}
		return null;
	}
	
	public String createToken() {
		try {

			Algorithm algorithmRS = Algorithm.HMAC256(environment.getProperty("jwt.secret"));

			DateTime now = DateTime.now();
			
			String token = JWT.create().withIssuer("auth0").withClaim("admin", true).withExpiresAt(now.plusMinutes(15).toDate()).withClaim("institution", environment.getProperty("institution.name")).sign(algorithmRS);

			return token;
		} catch (Exception e) {
			log.error("Error occured while creating token", e);
			throw new RuntimeException("Error occured while creating token");
		}
	}
	
}
