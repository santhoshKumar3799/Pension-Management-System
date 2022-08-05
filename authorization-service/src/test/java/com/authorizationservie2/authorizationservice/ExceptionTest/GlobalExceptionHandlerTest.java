package com.authorizationservie2.authorizationservice.ExceptionTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.junit4.SpringRunner;

import com.authorizationservie2.authorizationservice.exception.AuthorizationException;
import com.authorizationservie2.authorizationservice.exception.ExceptionDetails;
import com.authorizationservie2.authorizationservice.exception.GlobalExceptionHandler;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTest {

	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	
	@Mock
	ExceptionDetails customErrorResponse;
	
	@Before
	public void setUp() {
		customErrorResponse = new ExceptionDetails(LocalDateTime.now(), "test message");
	}
	
	
	
	@Test
	public void handleAuthorizationExceptionTest() {
		AuthorizationException e = new AuthorizationException("message");
		globalExceptionHandler.handleGlobalException(e, null);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCodeValue());
	}
	
	@Test
	public void handlesExceptionTest() {
		Exception e = new Exception("message");
		globalExceptionHandler.handleGlobalException(e, null);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCodeValue());
	}
}
