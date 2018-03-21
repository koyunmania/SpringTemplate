package com.spring.template.service;

import com.spring.template.model.Role;
import com.spring.template.model.RoleList;

public interface RoleService {
	public Role findRoleByRole(RoleList roleName);
	public void saveRole(Role role);
}
