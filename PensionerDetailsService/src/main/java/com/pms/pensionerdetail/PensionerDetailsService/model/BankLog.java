package com.pms.pensionerdetail.PensionerDetailsService.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;



@Component
@Entity
@Table(name = "banklog")
public class BankLog {
	// Pensioner Id
		@Id
		@Column(name = "id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		// Pensioner name
		@NotNull
		@Column(name = "name")
		private String name;

		// Pensioner Date Of Birth
		@NotNull
		@Column(name = "DOB")
		private Date dateOfBirth;

		// Pensioner PAN Number
		@NotNull
		@Column(name = "PAN_NUMBER")
		private String panNumber;

		// Pensioner Salary
		@NotNull
		@Column(name = "SALARY")
		private double salary;

		// Pensioner allowances
		@NotNull
		@Column(name = "ALLOWANCES")
		private double allowances;

		// Pensioner pension type
		@NotNull
		@Column(name = "PENSION_TYPE")
		private String typeOfPension;

		// Pensioner aadhaar number
		@NotNull
		@Column(name = "AADHAAR_NO")
		private long aadhaarNumber;

		// Pensioner bank name
		@NotNull
		@Column(name = "BANK_NAME")
		private String bankName;

		// Pensioner account number
		@NotNull
		@Column(name = "ACC_NO")
		private long accountNumber;

		// Pensioner bank service charge
		@NotNull
		@Column(name = "BANK_SERVICE_CHARGE")
		private double bankServiceCharge;

		// Pensioner pension amount
		@NotNull
		@Column(name = "PENSION_AMOUNT")
		private double pensionAmount;

		// Pensioner transaction date
		@NotNull
		@Column(name = "DATE_OF_TRANSACTION")
		private Date dateOfTransaction;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

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

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public double getAllowances() {
			return allowances;
		}

		public void setAllowances(double allowances) {
			this.allowances = allowances;
		}

		public String getTypeOfPension() {
			return typeOfPension;
		}

		public void setTypeOfPension(String typeOfPension) {
			this.typeOfPension = typeOfPension;
		}

		public long getAadhaarNumber() {
			return aadhaarNumber;
		}

		public void setAadhaarNumber(long aadhaarNumber) {
			this.aadhaarNumber = aadhaarNumber;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public long getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(long accountNumber) {
			this.accountNumber = accountNumber;
		}

		public double getBankServiceCharge() {
			return bankServiceCharge;
		}

		public void setBankServiceCharge(double bankServiceCharge) {
			this.bankServiceCharge = bankServiceCharge;
		}

		public double getPensionAmount() {
			return pensionAmount;
		}

		public void setPensionAmount(double pensionAmount) {
			this.pensionAmount = pensionAmount;
		}

		public Date getDateOfTransaction() {
			return dateOfTransaction;
		}

		public void setDateOfTransaction(Date dateOfTransaction) {
			this.dateOfTransaction = dateOfTransaction;
		}

		public BankLog(String name, Date dateOfBirth, String panNumber, double salary, double allowances,
				String typeOfPension, long aadhaarNumber, String bankName, long accountNumber, double bankServiceCharge,
				double pensionAmount, Date dateOfTransaction) {
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
			this.dateOfTransaction = dateOfTransaction;
		}
		
		
		public BankLog() {

		}

		@Override
		public String toString() {
			return "BankLog [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", panNumber=" + panNumber
					+ ", salary=" + salary + ", allowances=" + allowances + ", typeOfPension=" + typeOfPension
					+ ", aadhaarNumber=" + aadhaarNumber + ", bankName=" + bankName + ", accountNumber=" + accountNumber
					+ ", bankServiceCharge=" + bankServiceCharge + ", pensionAmount=" + pensionAmount
					+ ", dateOfTransaction=" + dateOfTransaction + "]";
		}
		
		
}
