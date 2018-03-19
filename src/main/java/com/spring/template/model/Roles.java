package com.spring.template.model;

public enum Roles {
	   User("ROLE_USER"), 
	   Admin("ROLE_ADMIN");
	   private String role;

	   private Roles(String role) {
	      this.role = role;
	   }
	   public String getName() {
	      return role;
	   }
}
