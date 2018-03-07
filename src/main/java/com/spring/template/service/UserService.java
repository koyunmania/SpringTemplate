package com.spring.template.service;

import com.spring.template.model.User;

public interface UserService {
	public ServiceResult findUserByEmail(String email);
	public ServiceResult findUserByUsername(String username);
	public ServiceResult saveUser(User user);
	public ServiceResult deleteUser(User user);
	
}
