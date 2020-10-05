package com.helpdesk.Helpdesk_v2.Entity;

public class loginRequest {

	private String username;
	private String password;
	
	public loginRequest() {
		// TODO Auto-generated constructor stub
	}
	
	

	public loginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
