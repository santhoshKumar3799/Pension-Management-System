package com.pms.pensionerdetail.PensionerDetailsService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import com.pms.pensionerdetail.PensionerDetailsService.Dao.PensionerDetailServiceDao;
import com.pms.pensionerdetail.PensionerDetailsService.restClients.AuthClient;

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

	@Autowired
	private AuthClient authClient;

	/**
	 * This method validates token
	 * 
	 * @param String token
	 * @return Boolean
	 * @throws Exception 
	 */
	public Boolean isSessionValid(String token) throws Exception {
		logger.info("START");

		try {
			authClient.getTokenValidity(token);
		} catch (RuntimeException e) {
			
			throw new Exception("Not Allowed");
		}
		logger.info("END");

		return true;
	}
}
