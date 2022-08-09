package com.pms.processpension.ProcessPensionService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RestController;

import com.pms.processpension.ProcessPensionService.Dao.ProcessPensionServiceDao;
import com.pms.processpension.ProcessPensionService.exception.AadharNumberNotFound;
import com.pms.processpension.ProcessPensionService.exception.AuthorizationException;
//import com.pms.processpension.ProcessPensionService.exception.PensionerDetailException;
import com.pms.processpension.ProcessPensionService.model.PensionDetail;
import com.pms.processpension.ProcessPensionService.model.PensionerInput;
import com.pms.processpension.ProcessPensionService.restClients.AuthClient;
import com.pms.processpension.ProcessPensionService.restClients.PensionerDetailClient;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProcessPensionController {
	private static Logger logger = LoggerFactory.getLogger(ProcessPensionController.class);

	@Autowired
	ProcessPensionServiceDao processPensionserviceDao;

	@Autowired
	PensionerDetailClient pensionerDetailClient;
	
	@Autowired
	private AuthClient authClient;

	/**
	 * The method at this end point calculates pension amount,and bank service
	 * charge
	 * 
	 * @param String
	 *            token, PensionerInput
	 * @return PensionDetail
	 */

	@PostMapping("/ProcessPension")
	@ApiOperation(notes = "Returns the Pension Details", value = "Find the pension details")
	@CrossOrigin
	public PensionDetail getPensionDetail(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@RequestBody PensionerInput pensionerInput)
			throws AuthorizationException, AadharNumberNotFound {
		
		logger.info("START");
		
		System.out.println("In process pension controller");
		
		if(authClient.getTokenValidity(requestTokenHeader)) {
			
			System.out.println("Authorization is  success");
			
			logger.info("END");
			return processPensionserviceDao.calculatePension(requestTokenHeader, pensionerInput);
		}else
		{
			logger.info("Not allowed");
			throw new AuthorizationException("Not allowed");
		}
	}
}
