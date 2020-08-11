package com.dispersion.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dispersion.model.LoginData;
import com.dispersion.service.ILoginService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ILoginService iLoginService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginData loginData) throws Exception {
		return new ResponseEntity<String>(iLoginService.checkLoginData(loginData), HttpStatus.OK);
	}
	
}
