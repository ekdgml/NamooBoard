package com.namoo.board.domain;

public class User {
	//
	private String userId;
	private String password;
	private String name;
	private String email;
	
	//-------------------------------------------------------------------------
	public User(String userId) {
		// 
		this.userId = userId;
	}

	public User(String userId, String password, String name, String email) {
		//
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	//-------------------------------------------------------------------------


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

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}
	//-------------------------------------------------------------------------

}
