package com.pms.pensionerdetail.PensionerDetailsService.Dao;

import javax.transaction.Transactional;

import com.pms.pensionerdetail.PensionerDetailsService.model.BankLog;

public interface BankLogServiceDao {
	@Transactional
	public boolean logTransaction(BankLog bankLog);

	public Boolean isSessionValid(String token);
}
