package com.pms.pensionerdetail.PensionerDetailsService.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.authorizationservie2.authorizationservice.model.UserData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.pms.pensionerdetail.PensionerDetailsService.PensionerDetailsServiceApplication;
//import com.pms.pensionerdetail.PensionerDetailsService.Dao.PensionerDetailServiceDao;
import com.pms.pensionerdetail.PensionerDetailsService.model.BankDetails;
import com.pms.pensionerdetail.PensionerDetailsService.model.PensionerDetail;
import com.pms.pensionerdetail.PensionerDetailsService.restClients.AuthClient;



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
	public  void readCsv() throws IOException, CsvValidationException, NumberFormatException, ParseException {
		PensionerDetailController.pensionersList.clear();

//		Resource resource = new ClassPathResource("details2.csv");
//		
//			File file = resource.getFile();
			InputStream file = getClass().getResourceAsStream("/details2.csv");
			Reader  reader1 = new InputStreamReader(file);
			CSVReader reader =new CSVReader(reader1) ;

			String[] pensionerString;
			PensionerDetail pensionerDetail;
			reader.readNext();

			while ((pensionerString = reader.readNext()) != null) // returns a Boolean value
			{
				pensionerDetail = new PensionerDetail(pensionerString[0],
						new SimpleDateFormat("dd-MM-yyyy").parse(pensionerString[1]), pensionerString[2],
						Double.parseDouble(pensionerString[3]), Double.parseDouble(pensionerString[4]),
						pensionerString[5],Long.parseLong(pensionerString[6]), new BankDetails(pensionerString[7],Long.parseLong(pensionerString[8]) , pensionerString[9]));
				PensionerDetailController.pensionersList.add(pensionerDetail);
			}
			
			reader.close();

		
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
				
				
				if (aadhaarNumber == pDetail.getAadhaarNumber()) {
					LOGGER.info("END");
					
					
					//return the pensioner detail
					//return pensionerDetail;
					return new ResponseEntity<PensionerDetail>(pDetail, HttpStatus.OK);
				}
					
			}
			
		}
		LOGGER.info("END");

		return new ResponseEntity<String>("Invalid token", HttpStatus.FORBIDDEN);
		
	}
	
	

}