package com.spring.template.service;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.template.model.Role;
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
	public User findUserByEmail(@Email String email) {
		User result = null; 
		try {
			result =  userRepository.findByEmail(email);
		} catch (DataAccessException e) {
			logger.error("__ERROR: UserServiceImpl.findUserByEmail(String email): ", e);
		}
		return result;
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		Role userRole = roleRepository.findByRole("ROLE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		try {
			userRepository.delete(user);
		} catch (DataAccessException e) {
			logger.error("__ERROR: UserServiceImpl.deleteUser(User user): ", e);
		}
		
	}
	
	@Override
	public User findUserByUsername(@Email String username) {
		User result = null;
		try {
			result = userRepository.findByUsername(username);
		} catch (Exception e) {
			logger.error("__ERROR: UserServiceImpl.findByUsername(String username): ", e);
		}
		return result;
	}
	
}
