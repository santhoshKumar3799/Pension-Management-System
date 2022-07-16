package com.pms.pensionerdetail.PensionerDetailsService.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;

import com.pms.pensionerdetail.PensionerDetailsService.Dao.BankLogServiceDao;
import com.pms.pensionerdetail.PensionerDetailsService.model.BankLog;
import com.pms.pensionerdetail.PensionerDetailsService.repository.BankRepository;
import com.pms.pensionerdetail.PensionerDetailsService.restClients.AuthClient;

@Component
public class BankLogServiceDaoImplementation implements BankLogServiceDao {
	private static Logger logger = LoggerFactory.getLogger(BankLogServiceDaoImplementation.class);
	
	
	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private AuthClient authClient;

	
	/**
	 * This method stores bank log in the database
	 * 
	 * @param BankLog
	 * @return Boolean
	 */
	
	@Transactional
	public boolean logTransaction(BankLog bankLog){
		logger.info("START");
		
		try {
			bankRepository.save(bankLog);
		} catch (UnexpectedRollbackException e) {
			logger.info("EXCEPTION");
			return false;
		}
		logger.info("END");

		return true;
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
