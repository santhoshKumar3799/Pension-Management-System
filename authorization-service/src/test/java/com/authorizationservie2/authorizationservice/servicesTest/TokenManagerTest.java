package com.authorizationservie2.authorizationservice.servicesTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import org.junit.Test;



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

	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbnVzZXIxIiwiZXhwIjoxNjU5NjYzMjU4LCJpYXQiOjE2NTk2MjcyNTh9.0sXltc9n-cGI3UgXUVvkHkUrmukqBkqkFbAj4YRNEiPBfvdGza2gglqOOdCZ8-Z98zl6Ve2H56MStJmYBcDNXA";

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
