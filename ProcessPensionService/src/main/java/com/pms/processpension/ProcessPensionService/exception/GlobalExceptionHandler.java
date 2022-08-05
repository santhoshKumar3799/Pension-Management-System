package com.pms.processpension.ProcessPensionService.exception;

import java.time.LocalDateTime;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ExceptionDetails> handleAuthorizationException(AuthorizationException ex){
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.FORBIDDEN, ex.getMessage());
		logger.error(ex.getMessage());
		return new ResponseEntity<ExceptionDetails>(exceptionDetail, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ExceptionDetails> handleMissingRequestHeaderException(MissingRequestHeaderException ex){
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
		logger.error(ex.getMessage());
		return new ResponseEntity<ExceptionDetails>(exceptionDetail, HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(PensionerDetailException.class)
//	public ResponseEntity<ExceptionDetails> handleSpecialistDetailNotFoundException(PensionerDetailException p){
//		
//		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND, p.getMessage());
//		logger.error(p.getMessage());
//		return new ResponseEntity<ExceptionDetails>(exceptionDetail, HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler(AadharNumberNotFound.class)
	public ResponseEntity<ExceptionDetails> handleMissingRequestHeaderException(AadharNumberNotFound ex){
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage());
		logger.error(ex.getMessage());
		return new ResponseEntity<ExceptionDetails>(exceptionDetail, HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(FeignException.class)
//    public ResponseEntity<ExceptionDetails> handleFeignStatusException(FeignException ex, HttpServletResponse response) {
//		logger.error("handles by handleFeignStatusException");
//		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage());
//		logger.error(ex.getMessage());
//		return new ResponseEntity<ExceptionDetails>(exceptionDetail, HttpStatus.BAD_REQUEST);
//    }

}
