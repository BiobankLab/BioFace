package com.dispersion.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dispersion.service.ITokenGenerator;

@Controller
@RequestMapping("/generateToken")
public class TokenGeneratorController {

	@Autowired
	private ITokenGenerator iTokenGenerator;
	
	@RequestMapping(value = "/generate", produces =  { "application/json" }, method = RequestMethod.GET) 
	public ResponseEntity<String> generateToken(Principal principal) {
		return new ResponseEntity<String>(iTokenGenerator.generateToken(), HttpStatus.OK);
	}
	
}
