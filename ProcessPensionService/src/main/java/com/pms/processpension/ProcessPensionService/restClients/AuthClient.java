package com.pms.processpension.ProcessPensionService.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authorization-service")
public interface AuthClient {
	@GetMapping("/validate")
	public Boolean getTokenValidity(@RequestHeader("Authorization") String token);
}
