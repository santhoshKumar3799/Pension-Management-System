package com.pms.processpension.ProcessPensionService.model;

import org.springframework.stereotype.Component;



@Component
public class PensionerInput {
	private long aadhaarNumber;

	public long getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public PensionerInput(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;

	}

	public PensionerInput() {

	}

}
