package com.pms.processpension.ProcessPensionService.Dao;



import org.springframework.stereotype.Component;

import com.pms.processpension.ProcessPensionService.exception.AadharNumberNotFound;
import com.pms.processpension.ProcessPensionService.exception.AuthorizationException;
import com.pms.processpension.ProcessPensionService.exception.PensionerDetailException;
import com.pms.processpension.ProcessPensionService.model.PensionDetail;
import com.pms.processpension.ProcessPensionService.model.PensionerInput;



@Component
public interface ProcessPensionServiceDao {
	public PensionDetail calculatePension(String token,PensionerInput pensionerInput) 
			throws PensionerDetailException, AuthorizationException, AadharNumberNotFound;

	public Boolean isSessionValid(String token);
}
