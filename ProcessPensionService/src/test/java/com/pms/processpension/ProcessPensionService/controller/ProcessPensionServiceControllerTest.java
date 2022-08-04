package com.pms.processpension.ProcessPensionService.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.processpension.ProcessPensionService.exception.AadharNumberNotFound;
import com.pms.processpension.ProcessPensionService.model.BankDetails;
import com.pms.processpension.ProcessPensionService.model.PensionerDetail;
import com.pms.processpension.ProcessPensionService.model.PensionerInput;
import com.pms.processpension.ProcessPensionService.restClients.AuthClient;
import com.pms.processpension.ProcessPensionService.restClients.PensionerDetailClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Date;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ProcessPensionServiceControllerTest {
	
	@MockBean
	private AuthClient authClient;
	
	@MockBean
	private PensionerDetailClient pensionerDetailClient;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testClientNotNull() {
		assertThat(authClient).isNotNull();
	}
	
	@Test
	public void testpensionerDetailFeignClient() {
		assertThat(pensionerDetailClient).isNotNull();
	}
	@Test
	public void testMockMvcNotNull() {
		assertThat(mockMvc).isNotNull();
	}
	
	  String mapToJson(Object object) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(object);
	    }
	
	  @Test 
		public void testGetResponse() throws Exception {
			
			when(authClient.getTokenValidity("Bearer @token@token")).thenReturn(true);
			PensionerDetail pensionerDetail = new PensionerDetail("Mounika",new Date(1970-05-27),"GTYIK7412L",70000,12000,
					"family",986543276547L,new BankDetails("SBI",102233445566L,"public"));
			when(pensionerDetailClient.getPensionerDetailByAadhaar("Bearer @token@token", 986543276547L)).thenReturn(pensionerDetail);
			PensionerInput pensionerInput = new PensionerInput();
			pensionerInput.setAadhaarNumber(986543276547L);
	//				pensionerInput.setName("Parthik");
	//				pensionerInput.setPan("BSDPS1495K");
	//				pensionerInput.setPensionType("self");
			String jsonPensionerInput = this.mapToJson(pensionerInput);
			mockMvc.perform(post("/ProcessPension").contentType("application/json").content(jsonPensionerInput).header("Authorization", "Bearer @token@token"))
			.andExpect(status().isOk());
		}
	  
	  @Test 
		public void testGetPensionDetailNotAuthorized() throws Exception {
			
			when(authClient.getTokenValidity("Bearer @token@token")).thenReturn(false);
			PensionerDetail pensionerDetail = new PensionerDetail("Mounika",new Date(1970-05-27),"GTYIK7412L",70000,12000,
					"family",986543276547L,new BankDetails("SBI",102233445566L,"public"));
			when(pensionerDetailClient.getPensionerDetailByAadhaar("Bearer @token@token", 986543276547L)).thenReturn(pensionerDetail);
			PensionerInput pensionerInput = new PensionerInput();
			pensionerInput.setAadhaarNumber(986543276547L);
	//				pensionerInput.setName("Parthik");
	//				pensionerInput.setPan("BSDPS1495K");
	//				pensionerInput.setPensionType("self");
			String jsonPensionerInput = this.mapToJson(pensionerInput);
			mockMvc.perform(post("/ProcessPension").contentType("application/json").content(jsonPensionerInput).header("Authorization", "Bearer @token@token"))
			.andExpect(status().isForbidden());
		}
	  
	  
	  @Test 
		public void testGetPensionDetailBadRequest() throws Exception {
			
			//when(authClient.getTokenValidity("Bearer @token@token")).thenReturn(false);
			PensionerDetail pensionerDetail = new PensionerDetail("Mounika",new Date(1970-05-27),"GTYIK7412L",70000,12000,
					"family",986543276547L,new BankDetails("SBI",102233445566L,"public"));
			when(pensionerDetailClient.getPensionerDetailByAadhaar("Bearer @token@token", 986543276547L)).thenReturn(pensionerDetail);
			PensionerInput pensionerInput = new PensionerInput();
			pensionerInput.setAadhaarNumber(986543276547L);
	//				pensionerInput.setName("Parthik");
	//				pensionerInput.setPan("BSDPS1495K");
	//				pensionerInput.setPensionType("self");
			String jsonPensionerInput = this.mapToJson(pensionerInput);
			mockMvc.perform(post("/ProcessPension").contentType("application/json").content(jsonPensionerInput))
			.andExpect(status().isBadRequest());
		}
	  
	  @Test 
		public void testGetPensionDetailAadharCard() throws Exception {
			
			when(authClient.getTokenValidity("Bearer @token@token")).thenReturn(true);
			when(pensionerDetailClient.getPensionerDetailByAadhaar("Bearer @token@token", 986543276547L)).thenThrow(AadharNumberNotFound.class);
//			PensionerDetail pensionerDetail = new PensionerDetail("Mounika",new Date(1970-05-27),"GTYIK7412L",70000,12000,
//					"family",986543276547L,new BankDetails("SBI",102233445566L,"public"));
			
			PensionerInput pensionerInput = new PensionerInput();
			pensionerInput.setAadhaarNumber(986543276547L);
	//				pensionerInput.setName("Parthik");
	//				pensionerInput.setPan("BSDPS1495K");
	//				pensionerInput.setPensionType("self");
			String jsonPensionerInput = this.mapToJson(pensionerInput);
			mockMvc.perform(post("/ProcessPension").contentType("application/json").content(jsonPensionerInput).header("Authorization", "Bearer @token@token"))
			.andExpect(status().isNotFound());
		}
	  
//	  @Test 
//		public void testGetPensionDetailPensionerDetailNotFound() throws Exception {
//			
//			when(authClient.getTokenValidity("Bearer @token@token")).thenReturn(true);
//			PensionerDetail pensionerDetail = new PensionerDetail("Mounika",new Date(1970-05-27),"GTYIK7412L",70000,12000,
//					"family",986543276547L,new BankDetails("SBI",102233445566L,"public"));
//			when(pensionerDetailClient.getPensionerDetailByAadhaar("Bearer @token@token", 986543276547L)).thenReturn(pensionerDetail);
//			PensionerInput pensionerInput = new PensionerInput();
//			pensionerInput.setAadhaarNumber(986543276547L);
//	//				pensionerInput.setName("Parthik");
//	//				pensionerInput.setPan("BSDPS1495K");
//	//				pensionerInput.setPensionType("self");
//			String jsonPensionerInput = this.mapToJson(pensionerInput);
//			mockMvc.perform(post("/ProcessPension").contentType("application/json").content(jsonPensionerInput).header("Authorization", "Bearer @token@token"))
//			.andExpect(status().isOk());
//		}
	  

}
