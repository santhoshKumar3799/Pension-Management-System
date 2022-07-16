package com.pms.pensionerdetail.PensionerDetailsService.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pms.pensionerdetail.PensionerDetailsService.model.AuthResponse;

/**
 * To access Authorization-microservice
 * 
 */

@FeignClient(name = "authorization-service", url = "http://localhost:8000")
@Component
public interface AuthClient {
	@GetMapping("/validate")
	public AuthResponse getTokenValidity(@RequestHeader("Authorization") String token);
}
