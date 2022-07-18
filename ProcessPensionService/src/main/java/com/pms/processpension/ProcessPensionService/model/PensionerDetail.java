package com.pms.processpension.ProcessPensionService.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;


import io.swagger.annotations.ApiModel;

@Component
@ApiModel(description = "Details about the pensioner")
public class PensionerDetail {
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "YYYY-MM-dd" , timezone="IST")
	private Date dateOfBirth;
	private String pan;
	private double salary;
	private double allowance;
	private String pensionType;
	private Long aadhaarNumber;
	private BankDetails bank;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getAllowance() {
		return allowance;
	}
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	public String getPensionType() {
		return pensionType;
	}
	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}
	public BankDetails getBank() {
		return bank;
	}
	public void setBank(BankDetails bank) {
		this.bank = bank;
	}
	
	public Long getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(Long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public PensionerDetail(String name, Date dateOfBirth, String pan, double salary, double allowance,
			String pensionType,Long aadhaarNumber, BankDetails bank) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.pan = pan;
		this.salary = salary;
		this.allowance = allowance;
		this.pensionType = pensionType;
		this.aadhaarNumber = aadhaarNumber;
		this.bank = bank;
	}
	
	public PensionerDetail() {
		
	}
	@Override
	public String toString() {
		return "PensionerDetail [name=" + name + ", dateOfBirth=" + dateOfBirth + ", pan=" + pan + ", salary=" + salary
				+ ", allowance=" + allowance + ", pensionType=" + pensionType + ", aadhaarNumber=" + aadhaarNumber
				+ ", bank=" + bank + "]";
	}
	
	
	
		
}