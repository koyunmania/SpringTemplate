package com.spring.template.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.template.model.Role;
import com.spring.template.model.RoleList;
import com.spring.template.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findRoleByRole(RoleList roleName) {
		Role result = null;
		try {
			result = roleRepository.findByRole(roleName.getName());
		} catch (Exception e) {
			logger.error("__ERROR: RoleServiceImpl.findByRole(String roleName): ", e);
		}
		return result;
	}

	@Override
	public void saveRole(Role role) {
		try {
			roleRepository.save(role);
		} catch (Exception e) {
			logger.error("__ERROR: RoleServiceImpl.saveRole(Role role): ", e);
		}
	}

}
