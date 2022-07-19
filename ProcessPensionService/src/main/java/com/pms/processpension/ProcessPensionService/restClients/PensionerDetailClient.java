package com.pms.processpension.ProcessPensionService.restClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pms.processpension.ProcessPensionService.exception.AadharNumberNotFound;
import com.pms.processpension.ProcessPensionService.exception.AuthorizationException;
import com.pms.processpension.ProcessPensionService.model.PensionerDetail;

//@FeignClient(name = "pensioner-detail-service", url = "http://localhost:8100")
@FeignClient(name = "pensioner-detail-service")
@Component
public interface PensionerDetailClient {
	
	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader("Authorization") String token,
			@PathVariable("aadhaarNumber") long aadhaarNumber) throws AuthorizationException, AadharNumberNotFound;
}
