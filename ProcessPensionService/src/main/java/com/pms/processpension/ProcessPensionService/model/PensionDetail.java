package com.pms.processpension.ProcessPensionService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;

@Component
@ApiModel(description = "Pension details")
@Entity
@Table(name = "PENSION_DETAIL")
public class PensionDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private double pensionAmount;
	private double bankServiceCharge;
	public double getPensionAmount() {
		return pensionAmount;
	}
	public void setPensionAmount(double pensionAmount) {
		this.pensionAmount = pensionAmount;
	}
	public double getBankServiceCharge() {
		return bankServiceCharge;
	}
	public void setBankServiceCharge(double bankServiceCharge) {
		this.bankServiceCharge = bankServiceCharge;
	}
	
	public PensionDetail(double pensionAmount, double bankServiceCharge) {
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
