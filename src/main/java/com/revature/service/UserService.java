package com.revature.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;

/**
 * This class is used as abstraction to hide the implementation
 * details, that accessess the database to for user information.
 */
public class UserService {

	private UserDao dao;

	public UserService(){
		this.dao = new UserDao();
	}
	
	/**
	 * Interacts with the dao that retrieves user information.
	 * @param username from ctx.body POST:/login
	 * @return User object
	 */
	public User getUserByUsername(String username) {
		return this.dao.getUserByUsername(username);
	}

	/**
	 * Interacts with the dao that adds user information.
	 * @param registerRequest from ctx.body POST:/register
	 * @return User object
	 */
	public User register(UsernamePasswordAuthentication registerRequest) {
		return this.dao.createUser(registerRequest);
	}
}
