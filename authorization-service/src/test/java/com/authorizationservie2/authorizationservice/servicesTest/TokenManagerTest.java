package com.authorizationservie2.authorizationservice.servicesTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorizationservie2.authorizationservice.jwtutilservices.JwtUserDetailsService;
import com.authorizationservie2.authorizationservice.jwtutilservices.TokenManager;
import com.authorizationservie2.authorizationservice.model.UserData;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenManagerTest {

	@Autowired(required = true)
	TokenManager tokenManager;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Test
	public void contextLoads() {

		assertNotNull(tokenManager);

	}

	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbnVzZXIxIiwiZXhwIjoxNjU5NDU5NzI0LCJpYXQiOjE2NTk0MjM3MjR9.j7fVX_Hzs6WVAMUvuU-dt7VtIjpye3EZB_4KU9ThM_q004uInOJySR5PGASjeEEBrYReIfrGeQMW4Salk6nFng";

	@Test
	public void extractUsernameTestSuccess() {

		assertEquals("adminuser1", tokenManager.extractUsername(token));
	}

	@Test
	public void generateTokenTestSuccess() {
		UserData userData = new UserData("adminuser1", "adminuser1", "adminuser1", "adminuser1");
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userData.getUserid());
		String exampleToken = tokenManager.generateJwtToken(userDetails);
		assertNotNull(exampleToken);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void generateTokenTestFail() {

		UserData userData = new UserData("randomUser", "randomUser", "randomUser", "randomUser");
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userData.getUserid());
		String exampleToken = tokenManager.generateJwtToken(userDetails);
		assertNull(exampleToken);

	}

	@Test
	public void validateTokenTestSuccess() {
		assertTrue(tokenManager.validateJwtToken(token));
	}

	@Test
	public void validateTokenTestFail() {
		assertFalse(tokenManager.validateJwtToken("randomToken"));
	}
}
