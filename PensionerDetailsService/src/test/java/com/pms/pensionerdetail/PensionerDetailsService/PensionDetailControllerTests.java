package com.pms.pensionerdetail.PensionerDetailsService;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import com.pms.pensionerdetail.PensionerDetailsService.controller.PensionerDetailController;
import com.pms.pensionerdetail.PensionerDetailsService.model.PensionerDetail;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PensionDetailControllerTests {
	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbnVzZXIxIiwiZXhwIjoxNjU5NzIwNjgzLCJpYXQiOjE2NTk2ODQ2ODN9.3YZuVblJvHz_RW6thX3ffgmrmeS2RnHr1IJmIKDIJzNkWvvNP7e9AEfE7AvNKfAak4howuUOuiW72efIcI4ruQ";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PensionerDetailController pensionerDetailController;

	@Test
	public void contextLoads() {

		assertNotNull(pensionerDetailController);

	}

	@Test
	public void pensionerDetailByAadhaarTestSuccess() throws Exception {
		mockMvc.perform(get("/PensionerDetailByAadhaar?aadhaarNumber=1234567891011120").header("Authorization",
				"Bearer " + token)).andExpect(status().isOk())
				.andExpect(jsonPath("$.aadhaarNumber").exists())
				.andExpect(jsonPath("$.aadhaarNumber").value("1234567891011120"));

	}

	@Test
	public void pensionerDetailByAadhaarTestFail() throws Exception {
		mockMvc.perform(get("/PensionerDetailByAadhaar?aadhaarNumber=7897897897897897").header("Authorization", "Bearer " + token))
				.andExpect(status().isForbidden())
				.andExpect(jsonPath("$.aadhaarNumber").doesNotExist());
	}
	
//	@Test
//	public void tokenIsNullTest() throws Exception {
//		mockMvc.perform(get("/PensionerDetailByAadhaar?aadhaarNumber=1234567891011120").header("Authorization", null))
//				.andExpect(status().isBadRequest());
//				
//	}
	
	@Test
	public void invalidToken() throws Exception{
		mockMvc.perform(get("/PensionerDetailByAadhaar?aadhaarNumber=1234567891011120").header("Authorization",
				"Bearer token" )).andExpect(status().isForbidden());
	}
	
//	@Test
//	public void noPensionerDetailListTest() throws Exception{
//		when(PensionerDetailController.pensionersList.size() <= 0).thenReturn(null);
////		mockMvc.perform(get("/PensionerDetailByAadhaar?aadhaarNumber=1234567891011120").header("Authorization",
////				"Bearer token" )).andExpect(status().isForbidden());
//	}
	
	
	@Test
	public void emptyPensionerList() throws Exception{
		List<PensionerDetail> pensionersList=null ;
//		PensionerDetail pensionerDetail = null;
//		for(int i = 0; i < pensionersList.size(); i++) {
//			pensionerDetail = pensionersList.get(i);
//		}
		assertNull(pensionersList);
	}

//	public static String pensionerAsJsonString(PensionerDetail pensioner) throws Exception {
//		
//			return new ObjectMapper().writeValueAsString(pensioner);
//		
//	}

}
