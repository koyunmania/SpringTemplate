package com.spring.template.service;

import com.spring.template.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
