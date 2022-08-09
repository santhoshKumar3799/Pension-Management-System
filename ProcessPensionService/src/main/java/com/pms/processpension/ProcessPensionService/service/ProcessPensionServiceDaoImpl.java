package com.pms.processpension.ProcessPensionService.service;

import java.util.HashMap;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pms.processpension.ProcessPensionService.Dao.ProcessPensionServiceDao;
import com.pms.processpension.ProcessPensionService.exception.AadharNumberNotFound;
import com.pms.processpension.ProcessPensionService.exception.AuthorizationException;

import com.pms.processpension.ProcessPensionService.model.PensionDetail;
import com.pms.processpension.ProcessPensionService.model.PensionerDetail;
import com.pms.processpension.ProcessPensionService.model.PensionerInput;
import com.pms.processpension.ProcessPensionService.repository.PensionDetailRepository;
import com.pms.processpension.ProcessPensionService.repository.PensionerDetailRepository;

import com.pms.processpension.ProcessPensionService.restClients.PensionerDetailClient;

@Service
public class ProcessPensionServiceDaoImpl implements ProcessPensionServiceDao {
	private static Logger logger = LoggerFactory.getLogger(ProcessPensionServiceDaoImpl.class);

	@Autowired
	private PensionDetail pensionDetail;

	@Autowired
	private PensionerDetailClient pensionerDetailClient;

	@Autowired
	private PensionerDetailRepository pensionerDetailsRepository;

	@Autowired
	private PensionDetailRepository pensionDetailsRepository;

	@SuppressWarnings("unused")
	@Autowired
	private PensionerDetail pensionerDetail;

	private static final Map<String, Double> banks = createMap();

	/**
	 * This method initializes a list of banks with their service charges
	 * 
	 * @return List og banks with service charge
	 */
	private static Map<String, Double> createMap() {
		logger.info("START");

		Map<String, Double> tempBanks = new HashMap<String, Double>();
		tempBanks.put("public", 500.0);
		tempBanks.put("private", 550.0);
		logger.info("END");

		return tempBanks;
	}

	/**
	 * This method retrieves service charge for a bankType
	 * 
	 * @param bankType
	 * @return service-charge
	 */

	public double getBankServiceCharge(String bankType) {
		if (banks.containsKey(bankType.toLowerCase()))
			return banks.get(bankType.toLowerCase());
		else
			return 0;
	}

	/**
	 * This method calculates the pension and returns the pension amount and bank
	 * service charge
	 * 
	 * And also saves the pensiondetail and pensioner detail to the in-memory
	 * database(H2)
	 * 
	 * @param pensionerInput
	 * @return pensionDetail
	 */

	public PensionDetail calculatePension(String token, PensionerInput pensionerInput)
			throws AuthorizationException, AadharNumberNotFound {

		logger.info("START");

		try {

			pensionerDetail = pensionerDetailClient.getPensionerDetailByAadhaar(token,
					pensionerInput.getAadhaarNumber());

			pensionerDetailsRepository.save(pensionerDetail);

			System.out.println("pensionerDetail" + pensionerDetail);

		} catch (Exception e) {

			logger.info("END");

			throw new AadharNumberNotFound("Aadhar Card Number is not Valid. Please check it and try again");

		}

		if (pensionerInput.getAadhaarNumber() == pensionerDetail.getAadhaarNumber()) {
			double salary = pensionerDetail.getSalary();
			double allowances = pensionerDetail.getAllowance();
			double pensionAmount = 0;

			// pension amount logic based on pension type
			if (pensionerDetail.getPensionType().equalsIgnoreCase("Self pension")) {
				pensionAmount = 0.8 * salary + allowances;

			} else if (pensionerDetail.getPensionType().equalsIgnoreCase("Family pension")) {
				pensionAmount = 0.5 * salary + allowances;
			}

			pensionDetail.setPensionAmount(pensionAmount);
			pensionDetail.setBankServiceCharge(getBankServiceCharge(pensionerDetail.getBank().getBankType()));

			pensionDetailsRepository.save(pensionDetail);

			logger.info("END");
			return pensionDetail;

		} else {

			logger.info("END");
			throw new AadharNumberNotFound("Aadhar Card Number is not Valid. Please check it and try again");

		}
	}

}
