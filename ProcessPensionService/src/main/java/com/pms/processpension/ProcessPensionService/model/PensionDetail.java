package com.pms.processpension.ProcessPensionService.model;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;

@Component
@ApiModel(description = "Pension details")
public class PensionDetail {
	private Double pensionAmount;
	private Double bankServiceCharge;
	public Double getPensionAmount() {
		return pensionAmount;
	}
	public void setPensionAmount(Double pensionAmount) {
		this.pensionAmount = pensionAmount;
	}
	public Double getBankServiceCharge() {
		return bankServiceCharge;
	}
	public void setBankServiceCharge(Double bankServiceCharge) {
		this.bankServiceCharge = bankServiceCharge;
	}
	
	public PensionDetail(Double pensionAmount, Double bankServiceCharge) {
		super();
		this.pensionAmount = pensionAmount;
		this.bankServiceCharge = bankServiceCharge;
	}
	
	public PensionDetail() {
		
	}
	
	@Override
	public String toString() {
		return "PensionDetail [pensionAmount=" + pensionAmount + ", bankServiceCharge=" + bankServiceCharge + "]";
	}
	
	
	
}
