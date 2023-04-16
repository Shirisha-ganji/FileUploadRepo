package com.UploadCSV.Entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_information")
public class UserInformation {
	
	@Id
	@GeneratedValue
	private int id;
	
	
	
	
	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;
	
	@Column(name = "Phonenumber")
	private String phoneNumber;

	public UserInformation()
	{
		
	}
	public UserInformation(String username, String email, String phoneNumber) {
		super();
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "UserInformation [username=" + username + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
	
	

}
