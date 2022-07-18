package com.pms.pensionerdetail.PensionerDetailsService.service;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



import com.pms.pensionerdetail.PensionerDetailsService.Dao.PensionerDetailServiceDao;
import com.pms.pensionerdetail.PensionerDetailsService.exception.NotFoundException;
import com.pms.pensionerdetail.PensionerDetailsService.model.BankDetails;
import com.pms.pensionerdetail.PensionerDetailsService.model.PensionerDetail;
import com.pms.pensionerdetail.PensionerDetailsService.restClients.AuthClient;
import com.pms.pensionerdetail.PensionerDetailsService.util.DateUtil;

/**
 * 
 *         PensionDetailsService is a class which contain the
 *         getPensionerDetailByAadhaarNumber function to get the pensioner
 *         details
 * 
 */
@Service
public class PensionerDetailServiceDaoImpl implements PensionerDetailServiceDao {
	private static Logger logger = LoggerFactory.getLogger(PensionerDetailServiceDaoImpl.class);
	
	
	@Value("${errorMessage}")
	private String AADHAAR_NUMBER_NOT_FOUND;
	
	
	@Autowired
	private AuthClient authClient;
	
	/**
	 * Loads pensioner from the details if it exists. After loading the details,
	 * compares the given Aadhaar Number from the Details CSV Files.
	 * 
	 * @param Aadhaar Number
	 * @return Pensioner Details
	 */
	public PensionerDetail getPensionerDetailByAadhaarNumber(String aadhaarNumber) {
		String line = "";
		BufferedReader br = new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream("/details.csv")));
		try {
			while ((line = br.readLine()) != null)// returns a Boolean value 
			{
				// convert record into strings
				String[] person = line.split(",");
				// if Aadhaar number is found, then return the details
				if (aadhaarNumber.contentEquals(person[0])) {
					logger.info("Details found");
					return new PensionerDetail(person[1], DateUtil.parseDate(person[2]), person[3],
							Double.parseDouble(person[4]), Double.parseDouble(person[5]), person[6],
							new BankDetails(person[7], Long.parseLong(person[8]), person[9]));
				}
			}
			
		}catch(NumberFormatException | IOException | ParseException e) {
			throw new NotFoundException(AADHAAR_NUMBER_NOT_FOUND);
		}throw new NotFoundException(AADHAAR_NUMBER_NOT_FOUND);
	}

	/**
	 * This method validates token
	 * 
	 * @param String token
	 * @return Boolean
	 */
	public Boolean isSessionValid(String token) {
		logger.info("START");

		try {
			authClient.getTokenValidity(token);
		} catch (Exception e) {
			logger.info("EXCEPTION");

			return false;
		}
		logger.info("END");

		return true;
	}
	
	
	
}
