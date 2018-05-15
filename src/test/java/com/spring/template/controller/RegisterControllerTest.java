package com.spring.template.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.template.service.RoleService;
import com.spring.template.service.StorageService;
import com.spring.template.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private StorageService storageService;
	
	@MockBean
    private UserService userservice;
	
	@MockBean
    private RoleService roleservice;
	
	@Test
	@WithMockUser(username = "a@a.com", roles = "USER")
	public void testRegisterGet() {
		try {
			mvc.perform(
					get("/register"))
			.andExpect(status().isOk())
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("register"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
