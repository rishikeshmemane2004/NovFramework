package com.qa.api.gorest.pojo;

import java.util.Map;

/**
 * this is a Pojo template for User class
 * @author Rishikessh
 *
 */
public class User implements Cloneable{

	private String name;
	private String email;
	private String gender;
	private String status;
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public User(Map<String, String> map) 
	{
		this.name   = map.get("name");
		this.email  = map.get("email");
		this.gender = map.get("gender");
		this.status = map.get("status");
	}
	
	public User(String name, String email, String gender, String status) 
	{
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
