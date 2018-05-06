package com.spring.template.api;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.spring.template.model.User;
import com.spring.template.service.ServiceResult;
import com.spring.template.service.StorageService;
import com.spring.template.service.UserService;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserServiceRestController.class)
//@WithMockUser(username = "a@a.com", roles="USER")
public class UserServiceRestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private UserService userService;
	
	@MockBean
    private StorageService storageService;
	
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	public void testGetUser() throws Exception  {
		User foundUser = new User();
		String mockUsername = "b@a.com";
		foundUser.setUsername(mockUsername);
		
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setData(foundUser);
		
		given(userService.findUserByUsername(mockUsername)).willReturn(serviceResult);
		mvc.perform(
				get("/api/user/getuser")
				.with(user("a@a.com").password("a"))
				.param("username", mockUsername)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.username", is(mockUsername)));
		verify(userService, times(1)).findUserByUsername(anyObject());
	}
	
	@Test
	public void testSaveUser() throws Exception {
		User foundUser = new User();
		String mockUsername = "z@z.com";
		String mockPassword = "123";
		foundUser.setUsername(mockUsername);
		foundUser.setPassword(mockPassword);
		mvc.perform(
				post("/api/user/saveuser")
				.with(user("a@a.com").password("123"))
				//.param("username", mockUsername)
				.content("{ \"username\":\"" + mockUsername + "\", \"password\":\"" + mockPassword + "\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	/* @Test
	public void testDeleteUser() throws Exception {
		User foundUser = new User();
		String mockUsername = "b@a.com";
		foundUser.setUsername(mockUsername);
		foundUser.setPassword(bCryptPasswordEncoder.encode("123"));
		
		ServiceResult serviceResult = new ServiceResult();
		serviceResult.setData(foundUser);
		serviceResult.setStatus(false);
		serviceResult.setMessage("xxx");
		
		given(userService.deleteUser(foundUser)).willReturn(serviceResult);
		mvc.perform(
				delete("/api/user/deleteuser")
				.with(user("a@a.com").password("123"))
				.param("username", mockUsername)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status", is("false")));
		verify(userService, times(1)).deleteUser(anyObject());
	}*/

}
