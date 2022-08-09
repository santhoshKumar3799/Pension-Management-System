package com.pms.processpension.ProcessPensionService.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.pms.processpension.ProcessPensionService.exception.AadharNumberNotFound;
import com.pms.processpension.ProcessPensionService.exception.AuthorizationException;
import com.pms.processpension.ProcessPensionService.model.PensionerDetail;


@FeignClient(name = "pensioner-detail-service")
@Component
public interface PensionerDetailClient {
	
	@GetMapping("/PensionerDetailByAadhaar")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader("Authorization") String token,
			@RequestParam("aadhaarNumber") long aadhaarNumber) throws AuthorizationException, AadharNumberNotFound;
}
