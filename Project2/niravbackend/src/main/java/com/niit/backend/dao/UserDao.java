package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.User;
public interface UserDao {
	User authenticate(User user);
	void updateUser(User user);
	User registerUser(User user);
	User getUserById(int userid);
	List<User> getAllUsers();
}
