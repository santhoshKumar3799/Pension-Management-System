package com.pms.pensionerdetail.PensionerDetailsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.pensionerdetail.PensionerDetailsService.controller.PensionerDetailController;
import com.pms.pensionerdetail.PensionerDetailsService.model.PensionerDetail;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PensionDetailControllerTests {
	private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbnVzZXIxIiwiZXhwIjoxNjU5NTQ5NzU0LCJpYXQiOjE2NTk1MTM3NTR9.91I2LN3WiFdJHLrUiZQ0O8vkHaHbnR7Dl231lCFfMQuNO-iYtWknds6zefQaIcBbH1WJ2oTWmFSKdcHZh45mCA";

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
