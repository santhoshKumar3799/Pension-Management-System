package com.authorizationservie2.authorizationservice.controllerTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.authorizationservie2.authorizationservice.controller.AuthorizationController;
import com.authorizationservie2.authorizationservice.model.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthControllerTest {

//	@Test
//	void test() {
//		assertEquals("CD","CD");
//		//EXPECTED,ACTUAL VALUE
//	}
	
	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbnVzZXIxIiwiZXhwIjoxNjU5NDU5NzI0LCJpYXQiOjE2NTk0MjM3MjR9.j7fVX_Hzs6WVAMUvuU-dt7VtIjpye3EZB_4KU9ThM_q004uInOJySR5PGASjeEEBrYReIfrGeQMW4Salk6nFng";
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthorizationController authController;
	
	
	

	@Test
	public void contextLoads() {

		assertNotNull(authController);

	}
	
	@Test
	public void loginTestSuccess() throws Exception{
		UserData admin = new UserData("adminuser1", "adminuser1", "adminuser1", "adminuser1");
		
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin))).
		andExpect(status().isOk());
	}
	
	@Test
	public void loginTestFail() throws Exception{
		UserData admin = new UserData("adminuser1", "randomUser", "randomUser", "randomUser");
		
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin))).
		andExpect(status().isForbidden());
	}
	
	@Test
	public void validateTestSuccess() throws Exception {
		mockMvc.perform(get("/validate").header("Authorization", "Bearer " + token))
		.andExpect(status().isOk());

	}
	
	@Test
	public void validateTestFail() throws Exception {
		mockMvc.perform(get("/validate").header("Authorization", "randomToken"))
		.andExpect(status().isForbidden());

	}
	@Test
	public void tokenisNullTest() throws Exception{
		mockMvc.perform(get("/validate").header("Authorization", ""))
		.andExpect(status().isForbidden());
	}
	
	public static String asJsonString(UserData admin) throws Exception {
		
			return new ObjectMapper().writeValueAsString(admin);
		 
	}
	

}
