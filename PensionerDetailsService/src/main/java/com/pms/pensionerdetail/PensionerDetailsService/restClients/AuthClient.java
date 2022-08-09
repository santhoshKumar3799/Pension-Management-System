package com.pms.pensionerdetail.PensionerDetailsService.restClients;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;






/**
 * To access Authorization-microservice
 * 
 */

//@FeignClient(name = "authorization-service", url = "http://localhost:8000")
@FeignClient(name = "authorization-service")
@Component

public interface AuthClient {
	@GetMapping("/validate")
	public Boolean getTokenValidity(@RequestHeader("Authorization") String requestTokenHeader);
}
