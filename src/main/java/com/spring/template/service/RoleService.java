package com.spring.template.service;

import com.spring.template.model.Role;
import com.spring.template.model.RoleName;

public interface RoleService {
	public Role findRoleByName(RoleName roleName);
}
