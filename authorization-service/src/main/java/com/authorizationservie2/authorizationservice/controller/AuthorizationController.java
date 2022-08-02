package com.authorizationservie2.authorizationservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.authorizationservie2.authorizationservice.jwtutilservices.JwtUserDetailsService;
import com.authorizationservie2.authorizationservice.jwtutilservices.TokenManager;
import com.authorizationservie2.authorizationservice.model.AuthorizationResponse;
import com.authorizationservie2.authorizationservice.model.UserData;


@RestController
public class AuthorizationController {
	private static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	/**
	 * This endpoint is used to generate token for valid credentials
	 * 
	 * @param UserData
	 * @return token , HttpStatus
	 */

	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<?> login(@RequestBody UserData userlogincredentials) {
		logger.info("START");
		
		//load the user from database 
		final UserDetails userdetails = jwtUserDetailsService.loadUserByUsername(userlogincredentials.getUserid());
		String uid = "";
		String generateToken = "";
		//if the loaded user password is same as the entered password 
		if (userdetails.getPassword().equals(userlogincredentials.getUpassword())) {
			uid = userlogincredentials.getUserid();
			generateToken = tokenManager.generateJwtToken(userdetails);
			
			logger.info(generateToken);
			logger.info("END");
			
			return new ResponseEntity<UserData>(new UserData(uid, null, null, generateToken), HttpStatus.OK);
		}//if the loaded user password is not same as the entered password  
		else {
			logger.info("END - Wrong credentials");
			
			return new ResponseEntity<String>("Not Accesible", HttpStatus.FORBIDDEN);
		}

	}
	
	
	
	/**
	 * This endpoint is used to validate the token
	 * 
	 * @param String token
	 * @return AuthResponse, HttpStatus
	 */
	
	
	@GetMapping("/validate")
	@CrossOrigin
	public ResponseEntity<AuthorizationResponse> getTokenValidity(@RequestHeader("Authorization") String token) {
		logger.info("START");
		
		//create a instance of authorization response bean
		AuthorizationResponse res = new AuthorizationResponse();
		//if the token is null
		if (token.isEmpty()) {
			res.setValid(false);
			
			logger.info("END - Null Token");
			
			return new ResponseEntity<AuthorizationResponse>(res, HttpStatus.FORBIDDEN);
		}
		//if the token is not null
		else {
			String token1 = token.substring(7);
			//if the token is valid 
			if (tokenManager.validateJwtToken(token1)) {
				res.setUid(tokenManager.extractUsername(token1));
				res.setValid(true);
				res.setRole("admin");
			}
			//if the token is invalid
			else {
				res.setValid(false);
				
				logger.info("END - Token expired");
				
				return new ResponseEntity<AuthorizationResponse>(res, HttpStatus.FORBIDDEN);
			}
		}
		
		logger.info("END - Token accepted");
		
		return new ResponseEntity<AuthorizationResponse>(res, HttpStatus.OK);
	}
	

}
