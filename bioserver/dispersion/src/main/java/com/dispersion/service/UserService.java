package com.dispersion.service;

import com.dispersion.model.LoginData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserService implements IUserService {

    @Autowired
    private DataSource dataSource;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public Boolean addUser(LoginData user) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            assert user != null && StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getPassword());

            String checkUsernameUniqueSql = "SELECT USERNAME FROM LOGIN_DATA WHERE UPPER(USERNAME) = '" + user.getUsername().trim().toUpperCase() + "'";
            PreparedStatement getPasswordStatement = connection.prepareStatement(checkUsernameUniqueSql);

            final boolean userExists = getPasswordStatement.executeQuery().next();

            if (userExists) {
                return false;
            }

            String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            String insertUserSql = "INSERT INTO LOGIN_DATA(USERNAME,PASSWORD) VALUES (?,?)";
            PreparedStatement addUserStatement = connection.prepareStatement(insertUserSql);
            addUserStatement.setString(1, user.getUsername());
            addUserStatement.setString(2, encryptedPassword);

            addUserStatement.executeUpdate();

            return true;

        } catch (Exception e) {
            log.error("Error while creating new user", e);
            throw e;
        }
    }
}
