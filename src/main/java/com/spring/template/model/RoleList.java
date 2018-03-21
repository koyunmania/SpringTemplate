package com.spring.template.model;

public enum RoleList {
	   User("ROLE_USER"), 
	   Admin("ROLE_ADMIN");
	   private String role;

	   private RoleList(String role) {
	      this.role = role;
	   }
	   public String getName() {
	      return role;
	   }
}
