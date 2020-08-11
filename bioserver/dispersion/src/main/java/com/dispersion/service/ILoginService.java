package com.dispersion.service;

import java.sql.SQLException;

import com.dispersion.model.LoginData;

public interface ILoginService {

	String checkLoginData(LoginData loginData) throws SQLException;

}