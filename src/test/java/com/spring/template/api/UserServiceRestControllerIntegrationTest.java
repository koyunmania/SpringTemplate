package com.spring.template.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import com.spring.template.Application;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "a@a.com", roles="USER")
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class UserServiceRestControllerIntegrationTest {

	private MockMvc mockMvc;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
    }
    
    @Test
	public void testGetExistingUser() {
    	String mockUsername = "a@a.com";

			try {
				mockMvc.perform(
						get("/userapi/getuser")
						.param("username", mockUsername)
						.contentType(contentType)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.username", is(mockUsername)));
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    
    @Test
	public void testGetNotUser() {
    	String mockUsername = "a@b.com";

			try {
				mockMvc.perform(
						get("/userapi/getuser")
						.param("username", mockUsername)
						.contentType(contentType)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").doesNotExist());
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    
	@Test
	public void testDeleteNotExistingUser() {
		String mockUsername = "a@b.com";
		try {
			mockMvc.perform(
					delete("/userapi/deleteuser")
					.param("username", mockUsername)
					.contentType(contentType)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status", is(false)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteExistingUser() {
		String mockUsername = "z@z.com";
		
		try {
			// Save mock user
			mockMvc.perform(
					post("/userapi/saveuser")
					.contentType(contentType)
					.accept(MediaType.APPLICATION_JSON)
					.content("{\"username\":\"" + mockUsername  + "\",\"password\":\"123\"}")
					)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status", is(true)));
			
			// Test if saved
			mockMvc.perform(
					get("/userapi/getuser")
					.param("username", mockUsername)
					.contentType(contentType)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.username", is(mockUsername)));
			
			// Delete saved user
			mockMvc.perform(
					delete("/userapi/deleteuser")
					.param("username", mockUsername)
					.contentType(contentType)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status", is(true)));
			
			// Test if deleted
			mockMvc.perform(
					delete("/userapi/deleteuser")
					.param("username", mockUsername)
					.contentType(contentType)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status", is(false)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*@Test
	public void testSaveUser() {
		fail("Not yet implemented");
	}*/

}
