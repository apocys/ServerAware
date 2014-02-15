package com.aware.db.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String password;
	
	private String email;
	
	private Date date;
	
	private String status;
	
	private Date authenticationTime;
	
	private String regId;
	
	private double latitude;
	
	private double longitude;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Date getAuthenticationTime() {
		return authenticationTime;
	}



	public void setAuthenticationTime(Date authenticationTime) {
		this.authenticationTime = authenticationTime;
	}



	public String getRegId() {
		return regId;
	}



	public void setRegId(String regId) {
		this.regId = regId;
	}



	public double getLatitude() {
		return latitude;
	}



	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	public String toString(){
		return "name " + name+
				" id " +id +
				" latitude " +latitude +
				" longitude " + longitude;
	}
	

}