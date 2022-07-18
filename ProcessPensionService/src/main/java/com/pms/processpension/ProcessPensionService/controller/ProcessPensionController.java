package com.pms.processpension.ProcessPensionService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RestController;

import com.pms.processpension.ProcessPensionService.Dao.ProcessPensionServiceDao;
import com.pms.processpension.ProcessPensionService.exception.AadharNumberNotFound;
import com.pms.processpension.ProcessPensionService.exception.AuthorizationException;
import com.pms.processpension.ProcessPensionService.exception.PensionerDetailException;
import com.pms.processpension.ProcessPensionService.model.PensionDetail;
import com.pms.processpension.ProcessPensionService.model.PensionerInput;
import com.pms.processpension.ProcessPensionService.restClients.PensionerDetailClient;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProcessPensionController {
	private static Logger logger = LoggerFactory.getLogger(ProcessPensionController.class);

	@Autowired
	ProcessPensionServiceDao processPensionserviceDao;

	@Autowired
	PensionerDetailClient pensionerDetailClient;

	/**
	 * The method at this end point calculates pension amount,and bank service
	 * charge
	 * 
	 * @param String
	 *            token, name, dob, pan, aadhaar, type
	 * @return Pensioner
	 */

	// @PostMapping("/ProcessPension")
	// @ApiOperation(value = "Provides the pension amount and bankservice charge ",
	// notes = "Validates the pensioner details on the basis of aadhaar number",
	// response = PensionDetail.class)
	// public PensionDetail getPensionDetail(@RequestHeader("Authorization") String
	// token, @RequestBody String name,
	// @RequestBody String dob, @RequestBody String pan, @RequestBody Long aadhaar,
	// @RequestBody String type)
	// throws Exception {
	// try {
	// logger.info("START");
	// if (processPensionserviceDao.isSessionValid(token)) {
	// if (processPensionserviceDao.validatePensionerDetails(token, name,
	// new SimpleDateFormat("dd-MM-yyyy").parse(dob), pan, aadhaar, type)) {
	// PensionDetail pensionDetail =
	// processPensionserviceDao.calculatePension(token, aadhaar);
	// logger.info("END");
	// return pensionDetail;
	// }else {
	// logger.info("END");
	//
	// return null;
	// }
	// }else {
	// logger.info("END");
	//
	// return null;
	// }
	//
	// }catch(Exception e) {
	// logger.info("EXCEPTION");
	//
	// throw e;
	// }

	@PostMapping("/ProcessPension")
	@ApiOperation(notes = "Returns the Pension Details", value = "Find the pension details")
	public PensionDetail getPensionDetail(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@RequestBody PensionerInput pensionerInput)
			throws AuthorizationException, PensionerDetailException, AadharNumberNotFound {
		System.out.println("In process pension controller");
		if(processPensionserviceDao.isSessionValid(requestTokenHeader)) {
			System.out.println("Hlllo+++authorization suceess");
			return processPensionserviceDao.calculatePension(requestTokenHeader, pensionerInput);
		}else
		{
			throw new AuthorizationException("Not allowed");
		}
	}
}
