package com.pms.processpension.ProcessPensionService.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pms.processpension.ProcessPensionService.model.AuthorizationResponse;


//@FeignClient(name = "authorization-service", url = "http://localhost:8000")
@FeignClient(name = "authorization-service")
public interface AuthClient {
	@GetMapping("/validate")
	public AuthorizationResponse getValidity(@RequestHeader("Authorization") String token);
}
