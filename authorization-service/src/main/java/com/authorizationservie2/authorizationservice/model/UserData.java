package com.authorizationservie2.authorizationservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "USER_DATA")
public class UserData {
	// User Id
	@Id
	@Column(name = "USER_ID")
	private String userid;
	// User Name
	@Column(name = "USER_NAME")
	private String uname;
	// User Password
	@Column(name = "USER_PASSWORD")
	private String upassword;

	@Transient
	private String authToken;

	public UserData(String userid,String uname, String upassword, String authToken) {
		super();
		this.userid = userid;
		this.uname = uname;
		this.upassword = upassword;
		this.authToken = authToken;
	}

	public UserData() {

	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

}
