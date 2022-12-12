package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

	private int id;
	private String username;
	private String password;

	// created a constructor to simplify code in UserDao.java
	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}	
}
