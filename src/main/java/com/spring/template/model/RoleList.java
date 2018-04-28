package com.spring.template.model;

public enum RoleList {
	   User("ROLE_USER"), 
	   Admin("ROLE_ADMIN"),
	   ApiUser("ROLE_APIUSER"); 
	   private String role;

	   private RoleList(String role) {
	      this.role = role;
	   }
	   public String getName() {
	      return role;
	   }
}
