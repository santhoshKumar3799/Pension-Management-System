package com.pms.pensionerdetail.PensionerDetailsService.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;

/**
 *  This is the Model package PensionerDetails class
 *  which consist of pensioner details like Name, Date of Birth, PAN,
 *  Salary, Allowance Pension Type, Bank Details.
 *
 */

@Component
@ApiModel(description = "Details about the pensioner")
public class PensionerDetail {
	// Pensioner name
		private String name;
		// Pensioner Date Of Birth
		private Date dateOfBirth;
		// Pensioner PAN Number
		private String panNumber;
		// Pensioner Salary
		private Double salary;
		// Pensioner Allowances
		private Double allowances;
		// Pensioner Pension Type
		private String typeOfPension;
		// Pensioner Aadhaar Number
		private Long aadhaarNumber;
		// Pensioner Bank Name
		private String bankName;
		// Pensioner Bank Account Number
		private Long accountNumber;
		// Pensioner Bank service Charge
		private Double bankServiceCharge;
		// Pensioner Pension Amount
		private Double pensionAmount;
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
		public String getPanNumber() {
			return panNumber;
		}
		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}
		public Double getSalary() {
			return salary;
		}
		public void setSalary(Double salary) {
			this.salary = salary;
		}
		public Double getAllowances() {
			return allowances;
		}
		public void setAllowances(Double allowances) {
			this.allowances = allowances;
		}
		public String getTypeOfPension() {
			return typeOfPension;
		}
		public void setTypeOfPension(String typeOfPension) {
			this.typeOfPension = typeOfPension;
		}
		public Long getAadhaarNumber() {
			return aadhaarNumber;
		}
		public void setAadhaarNumber(Long aadhaarNumber) {
			this.aadhaarNumber = aadhaarNumber;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public Long getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(Long accountNumber) {
			this.accountNumber = accountNumber;
		}
		public Double getBankServiceCharge() {
			return bankServiceCharge;
		}
		public void setBankServiceCharge(Double bankServiceCharge) {
			this.bankServiceCharge = bankServiceCharge;
		}
		public Double getPensionAmount() {
			return pensionAmount;
		}
		public void setPensionAmount(Double pensionAmount) {
			this.pensionAmount = pensionAmount;
		}
		public PensionerDetail(String name, Date dateOfBirth, String panNumber, Double salary, Double allowances,
				String typeOfPension, Long aadhaarNumber, String bankName, Long accountNumber, Double bankServiceCharge,
				Double pensionAmount) {
			super();
			this.name = name;
			this.dateOfBirth = dateOfBirth;
			this.panNumber = panNumber;
			this.salary = salary;
			this.allowances = allowances;
			this.typeOfPension = typeOfPension;
			this.aadhaarNumber = aadhaarNumber;
			this.bankName = bankName;
			this.accountNumber = accountNumber;
			this.bankServiceCharge = bankServiceCharge;
			this.pensionAmount = pensionAmount;
		}
		public PensionerDetail() {
			
		}
		@Override
		public String toString() {
			return "PensionerDetail [name=" + name + ", dateOfBirth=" + dateOfBirth + ", panNumber=" + panNumber
					+ ", salary=" + salary + ", allowances=" + allowances + ", typeOfPension=" + typeOfPension
					+ ", aadhaarNumber=" + aadhaarNumber + ", bankName=" + bankName + ", accountNumber=" + accountNumber
					+ ", bankServiceCharge=" + bankServiceCharge + ", pensionAmount=" + pensionAmount + "]";
		}
		
		
		

		
}
