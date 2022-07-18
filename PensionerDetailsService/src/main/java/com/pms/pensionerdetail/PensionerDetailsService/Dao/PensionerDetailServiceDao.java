package com.pms.pensionerdetail.PensionerDetailsService.Dao;

import com.pms.pensionerdetail.PensionerDetailsService.model.PensionerDetail;

public interface PensionerDetailServiceDao {
	public Boolean isSessionValid(String token);
	
	public PensionerDetail getPensionerDetailByAadhaarNumber(String aadhaarNumber);
}
