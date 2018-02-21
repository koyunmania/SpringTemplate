package com.spring.template.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.template.Application;
import com.spring.template.service.UserService;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
		  webEnvironment = WebEnvironment.RANDOM_PORT,
		  classes = Application.class)
@AutoConfigureMockMvc(secure=false)
@TestPropertySource(
		  locations = "classpath:application-integrationtest.properties")

// @WebMvcTest(UserServiceRestController.class)
public class UserServiceRestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private UserService userService;
	
	@Test
	public void testGetUser() throws Exception {
		// ResultActions x = mvc.perform(post("/userapi/post"));
		mvc.perform(post("/userapi/post")
			 .contentType(MediaType.APPLICATION_JSON))
			 .andExpect(status().isOk());
			 //.andExpect(jsonPath("$", hasSize(1)));
			 //.andExpect(jsonPath("$.username", is("a@a.com")));
				
	}

}
