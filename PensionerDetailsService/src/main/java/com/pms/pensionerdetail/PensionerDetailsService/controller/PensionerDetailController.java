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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.authorizationservie2.authorizationservice.model.UserData;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import com.pms.pensionerdetail.PensionerDetailsService.PensionerDetailsServiceApplication;

//import com.pms.pensionerdetail.PensionerDetailsService.Dao.PensionerDetailServiceDao;
import com.pms.pensionerdetail.PensionerDetailsService.model.BankDetails;
import com.pms.pensionerdetail.PensionerDetailsService.model.PensionerDetail;
import com.pms.pensionerdetail.PensionerDetailsService.restClients.AuthClient;

import io.swagger.annotations.ApiOperation;

@RestController
public class PensionerDetailController {
	private static Logger LOGGER = LoggerFactory.getLogger(PensionerDetailsServiceApplication.class);

	// creating a empty list of Pensioner type
	private static List<PensionerDetail> pensionersList = new ArrayList<PensionerDetail>();

//	@Autowired
//	private PensionerDetailServiceDao pensionerDetailServiceDao;
	
	@Autowired
	private AuthClient authClient;

	/**
	 * This method is used to load CSV data on start up
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * @throws CsvValidationException 
	 */

	@PostConstruct
	public void loadPensionerData() throws CsvValidationException, NumberFormatException, IOException, ParseException {
		LOGGER.info("START");
		readCsv();
	}

	/**
	 * This method reads data from CSV file
	 * @throws IOException 
	 * @throws CsvValidationException 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * 
	 */
	public static void readCsv() throws IOException, CsvValidationException, NumberFormatException, ParseException {
		PensionerDetailController.pensionersList.clear();

		Resource resource = new ClassPathResource("details2.csv");
		
			File file = resource.getFile();
			CSVReader reader = new CSVReader(new FileReader(file));

			String[] pensionerString;
			PensionerDetail pensionerDetail;
			String[] line = reader.readNext();

			while ((pensionerString = reader.readNext()) != null) // returns a Boolean value
			{
				pensionerDetail = new PensionerDetail(pensionerString[0],
						new SimpleDateFormat("dd-MM-yyyy").parse(pensionerString[1]), pensionerString[2],
						Double.parseDouble(pensionerString[3]), Double.parseDouble(pensionerString[4]),
						pensionerString[5],Long.parseLong(pensionerString[6]), new BankDetails(pensionerString[7],Long.parseLong(pensionerString[8]) , pensionerString[9]));
				PensionerDetailController.pensionersList.add(pensionerDetail);
			}
			
//			reader.close();

		
		LOGGER.info("END");
	}

	/**
	 * This endpoint is used to retrieve the pensioner details by using aadhaar
	 * number
	 * 
	 * @param String
	 *            token, aadhaar number
	 * @return PensionerDetails
	 * @throws Exception 
	 */
	@CrossOrigin
	@GetMapping("/PensionerDetailByAadhaar")
	@ApiOperation(value = "Provides the pensioner details", response = PensionerDetail.class)
	
	public ResponseEntity<?> getPensionerDetailByAadhaar(@RequestHeader("Authorization") String token,
			@RequestParam(name="aadhaarNumber") long aadhaarNumber) throws Exception {
		
		LOGGER.info("START");
		
		//if the token is valid
		if (authClient.getTokenValidity(token)) {
			
			PensionerDetail pensionerDetail;
			
			//loop through the pensioner list
			 
			 for (PensionerDetail pDetail : pensionersList){
				System.out.println(PensionerDetailController.pensionersList.size());
				LOGGER.info("PRINTED SIZE");
				
				//get the i th pensionerdetail
//				pensionerDetail = PensionerDetailController.pensionersList.get(i);
				
				//if the retreived pensionerdetail's aadhaar number 
				//is same as the request aadhar number
				if (aadhaarNumber == pDetail.getAadhaarNumber()) {
					LOGGER.info("END");
					
					
					//return the pensioner detail
					//return pensionerDetail;
					return new ResponseEntity<PensionerDetail>(pDetail, HttpStatus.OK);
				}
//					else {
//					LOGGER.info("END - Wrong aadhaar number");
//					
//					return new ResponseEntity<String>("Invalid aadhaar number", HttpStatus.FORBIDDEN);
//				}
			}
			
		}
		LOGGER.info("END");

		return new ResponseEntity<String>("Invalid token", HttpStatus.FORBIDDEN);
		
	}
	
	
//	
//	/**
//	 * The method at this end point updates pensioner detail to CSV
//	 * 
//	 * @param String token, Pensioner
//	 * @return Boolean
//	 */
//	@PostMapping("/UpdatePensioner")
//	@ApiOperation(value = "Updates the pensioner details in the CSV file")
//	public boolean updatePensioner(@RequestHeader("Authorization") String token,
//			@RequestBody PensionerDetail updatedPensioner) {
//		LOGGER.info("START");
//		if (bankLogServiceDao.isSessionValid(token)) {
//			try {
//				boolean flag = false;
//				for (PensionerDetail pensioner : PensionerDetailController.pensionersList) {
//					if (pensioner.getAadhaarNumber().equals(updatedPensioner.getAadhaarNumber())) {
//						PensionerDetailController.pensionersList.remove(pensioner);
//						PensionerDetailController.pensionersList.add(updatedPensioner);
//						flag = true;
//						break;
//					}
//				}
//				if (flag == false) {
//					LOGGER.info("END");
//					return flag;
//				} else {
//					Resource resource = new ClassPathResource("allPensionersData.csv");
//					File file;
//					file = resource.getFile();
//
//					CSVReader reader2 = null;
//					reader2 = new CSVReader(new FileReader(file));
//
//					List<String[]> allElements;
//					allElements = reader2.readAll();
//					allElements.clear();
//
//					FileWriter sw;
//					sw = new FileWriter(file);
//
//					CSVWriter writer = new CSVWriter(sw);
//					writer.writeAll(allElements);
//					String[] header = { "Name", "DateOfBirth", "PAN", "SalaryEarned", "Allowances", "PensionType",
//							"AadhaarNumber", "BankName", "BankAccountNumber", "BankServiceCharge", "PensionAmount" };
//					writer.writeNext(header);
//					for (PensionerDetail pensioners : PensionerDetailController.pensionersList) {
//						String dob = new SimpleDateFormat("dd-MM-yyyy").format(pensioners.getDateOfBirth());
//						String[] data = { pensioners.getName(), dob, pensioners.getPanNumber(),
//								Double.toString(pensioners.getSalary()), Double.toString(pensioners.getAllowances()),
//								pensioners.getTypeOfPension(), Long.toString(pensioners.getAadhaarNumber()),
//								pensioners.getBankName(), Long.toString(pensioners.getAccountNumber()),
//								Double.toString(pensioners.getBankServiceCharge()),
//								Double.toString(pensioners.getPensionAmount()) };
//						writer.writeNext(data);
//
//					}
//
//					writer.close();
//					readCsv();
//
//				}
//			} catch (IOException e) {
//				LOGGER.info("EXCEPTION");
//
//			} catch (CsvException e) {
//				LOGGER.info("EXCEPTION");
//			}
//			LOGGER.info("END");
//
//			return true;
//		}
//		LOGGER.info("END");
//
//		return false;
//
//	}
//	
//	
//	/**
//	 * The method at this end point logs bank transaction to the database
//	 * 
//	 * @param String token, Pensioner
//	 * @return Boolean
//	 */
//
//	@PostMapping("/LogTransaction")
//	@ApiOperation(value = "Saves the bank details to the database")
//	public boolean logTransaction(@RequestHeader("Authorization") String token, @RequestBody PensionerDetail pensioner) {
//		LOGGER.info("START");
//
//		if (bankLogServiceDao.isSessionValid(token)) {
//
//			if (bankLogServiceDao.logTransaction(
//					new BankLog(pensioner.getName(), new java.sql.Date(pensioner.getDateOfBirth().getTime()),
//							pensioner.getPanNumber(), pensioner.getSalary(), pensioner.getAllowances(),
//							pensioner.getTypeOfPension(), pensioner.getAadhaarNumber(), pensioner.getBankName(),
//							pensioner.getAccountNumber(), pensioner.getBankServiceCharge(),
//							pensioner.getPensionAmount(), new java.sql.Date((new Date()).getTime())))) {
//				LOGGER.info("END");
//				return true;
//			}
//			LOGGER.info("END");
//
//			return false;
//		}
//		LOGGER.info("END");
//
//		return false;
//	}
}
