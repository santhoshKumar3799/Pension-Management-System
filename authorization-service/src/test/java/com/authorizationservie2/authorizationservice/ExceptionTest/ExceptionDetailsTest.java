package com.authorizationservie2.authorizationservice.ExceptionTest;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.authorizationservie2.authorizationservice.exception.ExceptionDetails;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExceptionDetailsTest {

	private ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),"message");
	
	@Test
	public void testMessageSetter() {
		details.setMessage("new message");
		assertThat(details.getMessage().equals("new message"));
		
	}
	
	@Test
	public void testTimeStampSetter() {
		
		LocalDateTime date = LocalDateTime.now();
		details.setTimeStamp(date);
		assertThat(details.getTimeStamp().equals(date));
		
	}
}
