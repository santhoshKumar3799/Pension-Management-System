package com.pms.processpension.ProcessPensionService.model;












public class PensionerInput {
	private long aadhaarNumber;
//	private String name;
//	@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "YYYY-MM-dd" , timezone="IST")
//	private Date dateOfBirth;
//	private String pan;
//	private String pensionType;
	public long getAadhaarNumber() {
		return aadhaarNumber;
	}
	public void setAadhaarNumber(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public Date getDateOfBirth() {
//		return dateOfBirth;
//	}
//	public void setDateOfBirth(Date dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
//	public String getPan() {
//		return pan;
//	}
//	public void setPan(String pan) {
//		this.pan = pan;
//	}
//	public String getPensionType() {
//		return pensionType;
//	}
//	public void setPensionType(String pensionType) {
//		this.pensionType = pensionType;
//	}
	public PensionerInput(long aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
//		this.name = name;
//		this.dateOfBirth = dateOfBirth;
//		this.pan = pan;
//		this.pensionType = pensionType;
	}
	
	public PensionerInput() {
		
	}
	
}
