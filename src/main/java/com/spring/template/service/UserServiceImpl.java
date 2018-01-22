package com.spring.template.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.template.model.User;
import com.spring.template.repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;

	@Override
	public User findUserByEmail(String email) {
		User user = null; 
		try {
			user = userRepository.findByEmail(email);
		} catch (Exception e) {
			logger.error("__ERROR: UserServiceImpl - User can not be found in the database!");
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
}
