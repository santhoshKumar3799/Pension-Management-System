package com.authorizationservie2.authorizationservice.servicesTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorizationservie2.authorizationservice.jwtutilservices.JwtUserDetailsService;



@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtUserDetailsServiceTest {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Test
	public void contextLoads() {

		assertNotNull(jwtUserDetailsService);

	}
	
	
	@Test
	public void loadUserByUsernameTestSuccess() {

		assertEquals("adminuser1", jwtUserDetailsService.loadUserByUsername("adminuser1").getUsername());
	}

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsernameTestFail() throws UsernameNotFoundException  {
		assertEquals("randomUser", jwtUserDetailsService.loadUserByUsername("randomUser").getUsername());
	
	}
}
