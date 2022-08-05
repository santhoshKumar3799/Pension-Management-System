package com.pms.processpension.ProcessPensionService.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ProcessPensionExceptionTests {
	@InjectMocks
	private AadharNumberNotFound aadharNumberNotFound;
	
	@InjectMocks
	private AuthorizationException authorizationException;
	
	@InjectMocks
	private ExceptionDetails exceptionDetails;

	@Test
	public void testAadharNumberNotFound()
	{
		String reason = "Invalid aadharNumber";
		assertEquals(reason, "Invalid aadharNumber");
	}
	
	@Test
	public void testAuthorizationException()
	{
		String reason = "Invalid token";
		assertEquals(reason, "Invalid token");
	}
	@Test
	public void testNoArgsExceptionDetail() {
		assertThat(new ExceptionDetails()).isNotNull();
	}
	@Test
	public void testExceptionDetailSetter() {
		ExceptionDetails exceptionDetail = new ExceptionDetails();
		exceptionDetail.setTimeStamp(null);
		exceptionDetail.setMessage(null);
		exceptionDetail.setStatus(null);
	}
	
	@Test
	public void testExceptionDetail() {
		ExceptionDetails exceptionDetail = new ExceptionDetails(null, null, null);
		assertEquals(null, exceptionDetail.getTimeStamp());
		assertEquals(null, exceptionDetail.getStatus());
		assertEquals(null, exceptionDetail.getMessage());
	}

}
