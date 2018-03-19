package com.spring.template.service;

import com.spring.template.model.Role;
import com.spring.template.model.Roles;

public interface RoleService {
	public Role findRoleByRole(Roles roleName);
	public void saveRole(Role role);
}
