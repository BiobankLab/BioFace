package com.dispersion.service;

import com.dispersion.model.LoginData;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface IUserService {

    Boolean addUser(LoginData user) throws SQLException;
}
