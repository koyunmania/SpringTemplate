package com.spring.template.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import com.spring.template.model.User;
import com.spring.template.service.ServiceResult;
import com.spring.template.service.UserService;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserServiceRestController.class)
@WithMockUser(username = "a@a.com", roles="USER")
public class UserServiceRestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private UserService userService;
	
	@Test
	public void testGetUser() throws Exception  {
		User foundUser = new User();
		String mockUsername = "a@a.com";
		foundUser.setUsername(mockUsername);
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setData(foundUser);
		given(userService.findUserByUsername(mockUsername)).willReturn(serviceResult);
		mvc.perform(
				get("/userapi/getuser")
				//.with(user("a@a.com").password("a"))
				.param("username", mockUsername)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.username", is("a@a.com")));
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		User foundUser = new User();
		String mockUsername = "a@a.com";
		foundUser.setUsername(mockUsername);
		mvc.perform(
				delete("/userapi/deleteuser")
				.param("username", mockUsername)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void testSaveUser() throws Exception {
		User foundUser = new User();
		String mockUsername = "a@a.com";
		String mockPassword = "123";
		foundUser.setUsername(mockUsername);
		foundUser.setPassword(mockPassword);
		mvc.perform(
				post("/userapi/saveuser")
				//.param("username", mockUsername)
				.content("{ \"username\":\"" + mockUsername + "\", \"password\":\"" + mockPassword + "\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
