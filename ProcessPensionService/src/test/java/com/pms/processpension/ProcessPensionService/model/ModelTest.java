package com.pms.processpension.ProcessPensionService.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ModelTest {
	@Test
	public void testNoArgsBankDetailTest() {
		assertThat(new BankDetails()).isNotNull();
	}

	@Test
	public void testAllArgsBankDetailTest() {
		BankDetails bankDetails = new BankDetails("Bytecard", 2626564583L, "private") ;
		assertNotNull(bankDetails) ;
	}

	@Test
	public void testSetterBankTest() {
		BankDetails b = new BankDetails();
		b.setAccountNumber(102233445566L);
		b.setBankName("Bytecard");
		b.setBankType("private");
		assertThat(assertThat(b).isNotNull());

	}
//	
	@Test
	public void SetterArgsBankDetailTest() {
		BankDetails bankDetails = new BankDetails("SBI", 1234567890L, "private") ;
		assertEquals("SBI", bankDetails.getBankName()) ;
		assertEquals(1234567890, bankDetails.getAccountNumber()) ;
		assertEquals("private", bankDetails.getBankType()) ;
	}
	@Test
	public void testNoArgsPensionerInputTest() {
		assertThat(new PensionerInput()).isNotNull();
	}
	
	@Test
	public void testNoArgsPensionDetailTest() {
		assertThat(new PensionDetail()).isNotNull();
	}
	
	@Test
	public void testSetterPensionerDetailTest() {
		//pensionerDetail
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadhaarNumber(986543276547L);
		pensionerDetail.setDateOfBirth(new Date(1970-05-27));
		pensionerDetail.setName("Mounika");
		pensionerDetail.setPan("GTYIK7412L");
		pensionerDetail.setSalary(70000);
		pensionerDetail.setAllowance(12000);
		pensionerDetail.setPensionType("family");

		BankDetails bankDetail = new BankDetails();
		bankDetail.setAccountNumber(102233445566L);
		bankDetail.setBankName("SBI");
		bankDetail.setBankType("public");

		pensionerDetail.setBank(bankDetail);
		assertThat(assertThat(pensionerDetail).isNotNull());
		
		assertEquals(986543276547L ,pensionerDetail.getAadhaarNumber() ) ;
		assertEquals("Thu Jan 01 05:30:01 IST 1970", pensionerDetail.getDateOfBirth().toString()) ;
		assertEquals("Mounika", pensionerDetail.getName()) ;
		assertEquals("GTYIK7412L", pensionerDetail.getPan()) ;
		assertEquals(70000, pensionerDetail.getSalary()) ;
		assertEquals(12000, pensionerDetail.getAllowance()) ;
		assertEquals("family", pensionerDetail.getPensionType()) ;
		
		assertEquals(102233445566L,bankDetail.getAccountNumber() ) ;
		assertEquals("SBI", bankDetail.getBankName()) ;
		assertEquals("public",bankDetail.getBankType() ) ;
		
		assertNotNull(pensionerDetail.toString());
		
		//pensionerInput
		PensionerInput pensionerInput = new PensionerInput(986543276547L);	
		assertThat(assertThat(pensionerInput).isNotNull());
		assertEquals(986543276547L,pensionerInput.getAadhaarNumber());
		
		
		//pensiondetail
		PensionDetail pensionDetail = new PensionDetail(6000,100);
		assertThat(assertThat(pensionDetail).isNotNull());
		assertEquals(6000,pensionDetail.getPensionAmount());
		assertEquals(100,pensionDetail.getBankServiceCharge());
		assertNotNull(pensionDetail.toString());
		
		
		
		
		
		
	}

}
