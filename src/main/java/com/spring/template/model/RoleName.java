package com.spring.template.model;

public enum RoleName {
	   User("ROLE_USER"), 
	   Admin("ROLE_ADMIN");
	   private String role;

	   private RoleName(String role) {
	      this.role = role;
	   }
	   public String getRole() {
	      return role;
	   }
}
