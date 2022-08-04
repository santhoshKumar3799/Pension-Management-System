package com.pms.processpension.ProcessPensionService.serviceTest;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

import com.pms.processpension.ProcessPensionService.exception.AadharNumberNotFound;
import com.pms.processpension.ProcessPensionService.exception.AuthorizationException;
import com.pms.processpension.ProcessPensionService.model.BankDetails;
import com.pms.processpension.ProcessPensionService.model.PensionDetail;
import com.pms.processpension.ProcessPensionService.model.PensionerDetail;
import com.pms.processpension.ProcessPensionService.model.PensionerInput;
import com.pms.processpension.ProcessPensionService.restClients.PensionerDetailClient;
import com.pms.processpension.ProcessPensionService.service.ProcessPensionServiceDaoImpl;



@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ProcessPensionServiceTest {

	@InjectMocks
	private ProcessPensionServiceDaoImpl processPensionServiceImpl;
	
	@Mock
	private PensionerDetailClient pensionerDetailClient;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCalculatePensionForFamilyPension() throws  AuthorizationException, AadharNumberNotFound
	{
		String token = "dummy";
		PensionerInput pensionerInput = new PensionerInput(1234567891011120l);
		PensionDetail pensionDetail =new PensionDetail(6069.98d,550.0d);
		PensionerDetail pensionerDetail = new PensionerDetail("Mounika",new Date(1970-05-27),"GTYIK7412L",10000.0,1069.98,
				"Family pension",1234567891011120l,new BankDetails("Bytecard",102233445566L,"private"));
		Mockito.when(pensionerDetailClient.getPensionerDetailByAadhaar(token, 1234567891011120L)).thenReturn(pensionerDetail);
		System.out.println("hello"+processPensionServiceImpl.calculatePension(token, pensionerInput));
		System.out.println("hello11"+pensionDetail);
		PensionDetail expected = processPensionServiceImpl.calculatePension(token, pensionerInput);
		System.out.println(expected);
		System.out.println(pensionDetail);
		
	}
	
	@Test
	public void testCalculatePensionForSelfPension() throws  AuthorizationException, AadharNumberNotFound
	{
		String token = "dummy";
		PensionerInput pensionerInput = new PensionerInput(1234567891011120l);
		PensionDetail pensionDetail =new PensionDetail(6069.98d,550.0d);
		PensionerDetail pensionerDetail = new PensionerDetail("Mounika",new Date(1970-05-27),"GTYIK7412L",10000.0,1069.98,
				"Self pension",1234567891011120l,new BankDetails("Bytecard",102233445566L,"private"));
		Mockito.when(pensionerDetailClient.getPensionerDetailByAadhaar(token, 1234567891011120L)).thenReturn(pensionerDetail);
		System.out.println("hello"+processPensionServiceImpl.calculatePension(token, pensionerInput));
		System.out.println("hello11"+pensionDetail);
		PensionDetail expected = processPensionServiceImpl.calculatePension(token, pensionerInput);
		System.out.println(expected);
		System.out.println(pensionDetail);
		
	}
	
	@Test
	public void testAAdhaarNumberNotMatch() throws  AuthorizationException, AadharNumberNotFound
	{
		String token = "dummy";
		PensionerInput pensionerInput = new PensionerInput(1234567891011120L);
		PensionDetail pensionDetail =new PensionDetail(6069.98d,550.0d);
		PensionerDetail pensionerDetail = new PensionerDetail("Mounika",new Date(1970-05-27),"GTYIK7412L",10000.0,1069.98,
				"Self pension",12345678910111201L,new BankDetails("Bytecard",102233445566L,"private"));
		Mockito.when(pensionerDetailClient.getPensionerDetailByAadhaar(token, pensionerInput.getAadhaarNumber())).thenReturn(pensionerDetail);
		AadharNumberNotFound exception = assertThrows(AadharNumberNotFound.class, ()->{
			processPensionServiceImpl.calculatePension(token, pensionerInput);
		});
		
		
		assertEquals("Aadhar Card Number is not Valid. Please check it and try again", exception.getMessage());

		
	}
	
	
	@Test
	public void testGetBankServiceCharge() throws Exception{
		Map<String, Double> banks = new HashMap<String, Double>();
		banks.put("public", 500.0);
		banks.put("duplicate", 550.0);
		
		assertEquals(0, processPensionServiceImpl.getBankServiceCharge("duplicate"),0);
	}
	



}
