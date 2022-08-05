package com.pms.processpension.ProcessPensionService.model;

import java.util.Date;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;


import io.swagger.annotations.ApiModel;

@Component
@ApiModel(description = "Details about the pensioner")
@Entity
@Table(name = "PENSIONER_DETAIL")
public class PensionerDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	//@Column(name = "DOB")
	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "YYYY-MM-dd" , timezone="IST")
	private Date dateOfBirth;
	//@Column(name = "PAN")
	private String pan;
	//@Column(name = "SALARY")
	private double salary;
	//@Column(name = "ALLOWANCE")
	private double allowance;
	//@Column(name = "PENSION_TYPE")
	private String pensionType;
	//@Column(name = "NAME")
	private long aadhaarNumber;
	@Embedded
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
	
	public long getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
	public PensionerDetail(String name, Date dateOfBirth, String pan, double salary, double allowance,
			String pensionType,long aadhaarNumber, BankDetails bank) {
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