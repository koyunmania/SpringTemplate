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
	public void testGetUser() throws Exception {
		User user = new User();
		user.setUsername("a@a.com");
		given(userService.findUserByUsername("a@a.com")).willReturn(user);
		mvc.perform(post("/userapi/post")
			//.with(user("a@a.com").password("a"))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.username", is("a@a.com")));
	}

}
