package com.spring.template.service;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.template.errorhandling.validator.Validator;
import com.spring.template.errorhandling.validator.ValidatorResult;
import com.spring.template.model.Role;
import com.spring.template.model.Roles;
import com.spring.template.model.User;
import com.spring.template.repository.RoleRepository;
import com.spring.template.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
	public ServiceResult findUserByEmail(String email) {
    	ServiceResult result = new ServiceResult();
    	User user = new User();
    	String message = "";
    	
		try {
			user = userRepository.findByEmail(email);
			message = "User (mail: " + email + ") could be found successfully in DB.";
			result.setStatus(true);
			logger.info(message);
		} catch (DataAccessException e) {
			message = "User (mail: " + email + ") can't be found in DB.";
			result.setStatus(false);
			logger.error("UserServiceImpl.findUserByEmail(String email): " + message, e);
		}
		result.setData(user);
		result.setMessage(message);
		return result;
	}

	@Override
	public ServiceResult saveUser(User user) {
		ServiceResult result = new ServiceResult();
		String message = "";
		User foundUser = Validator.validateUserExistance(user); 
		
		if( foundUser != null ) {
			message = " User: " + user.getUsername() + " is already existing.";
			logger.warn("UserServiceImpl.saveUser(User user):" + message);
			result.setMessage(message);
			return result;
		} else {
			user.setEmail(user.getUsername());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setEnabled(true);
			Role userRole = roleRepository.findByRole(Roles.User.getName());
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		} 
		try {
			userRepository.save(user);
			result.setStatus(true);
			message = " User: " + user.getUsername() + " saved successfully.";
			logger.info(message);
		} catch (Exception e) {
			message = "User: " + user.getUsername() + " can't be saved.";
			result.setStatus(false);
			logger.error("UserServiceImpl.saveUser(User user): " + message, e);
		}
		result.setMessage(message);
		return result;
	}

	@Override
	public ServiceResult deleteUser(User user) {
		ServiceResult result = new ServiceResult();
		String message = "";
		User foundUser = Validator.validateUserExistance(user);
		if( foundUser == null ) {
			result.setStatus(false);
			message = " User: " + user.getUsername() + " could not be found.";
			logger.error("UserServiceImpl.deleteUser(User user): " + message);
		} else {
			try {
				userRepository.delete(foundUser);
				result.setStatus(true);
				message = " User: " + user.getUsername() + " deleted successfully.";
				logger.info("UserServiceImpl.deleteUser(User user): " + message);
			} catch (DataAccessException e) {
				result.setStatus(false);
				message = "User: " + user.getUsername() + " can't be deleted.";
				logger.error("UserServiceImpl.deleteUser(User user): " + message, e);
			}
		}
		
		result.setMessage(message);
		return result;
	}
	
	@Override
	public ServiceResult findUserByUsername(String username) {
		ServiceResult result = new ServiceResult();
		String message = "";

		User user = new User();
		try {
			user = userRepository.findByUsername(username);
			result.setStatus(true);
			result.setData(user);
			message = "Username: " + username + " queried successfully.";
			logger.info("UserServiceImpl.findByUsername(String username): " + message);
		} catch (Exception e) {
			result.setStatus(false);
			message = "Username: " + username + " can't be queried.";
			logger.error("UserServiceImpl.findByUsername(String username): " + message, e);
			;
		}
		
		result.setMessage(message);
		return result;
	}
}
