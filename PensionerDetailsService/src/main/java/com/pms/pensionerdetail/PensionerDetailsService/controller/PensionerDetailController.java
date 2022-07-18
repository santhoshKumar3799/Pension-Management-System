package com.pms.pensionerdetail.PensionerDetailsService.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.pms.pensionerdetail.PensionerDetailsService.PensionerDetailsServiceApplication;

import com.pms.pensionerdetail.PensionerDetailsService.Dao.PensionerDetailServiceDao;

import com.pms.pensionerdetail.PensionerDetailsService.model.PensionerDetail;

import io.swagger.annotations.ApiOperation;

@RestController
public class PensionerDetailController {
	private static Logger LOGGER = LoggerFactory.getLogger(PensionerDetailsServiceApplication.class);

	@Autowired
	private PensionerDetailServiceDao pensionerDetailServiceDao;

	/**
	 * @URL: http://localhost:8083/pensionerDetailByAadhaar/123456789012
	 * 
	 * @return if Aadhaar Number is valid then return the pensioner details else
	 *         throws Exception
	 * 
	 * @Expceted: { "name": "Santhosh", "dateOfBirth":
	 *            "1989-07-11T16:20:00.000+00:00", "pan": "GZTPS0809B", "salary":
	 *            32000, "allowance": 12000, "pensionType": "family", "bank": {
	 *            "bankName": "ICICI", "accountNumber": 324567890, "bankType":
	 *            "private" } }
	 *
	 */

	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	@ApiOperation(value = "Provides the pensioner details", response = PensionerDetail.class)
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader("Authorization") String token,
			@PathVariable String aadhaarNumber) {
		LOGGER.info("START");
		LOGGER.info("START - getPensionerDetailByAadhaar()");
		if (pensionerDetailServiceDao.isSessionValid(token)) {
			LOGGER.info("END - getPensionerDetailByAadhaar()");
			return pensionerDetailServiceDao.getPensionerDetailByAadhaarNumber(aadhaarNumber);
		}
		LOGGER.info("END");

		return null;

	}

}
