package com.spring.template.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import com.spring.template.Application;
import com.spring.template.model.Role;
import com.spring.template.model.RoleName;
import com.spring.template.model.User;
import com.spring.template.service.RoleService;
import com.spring.template.service.UserService;

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

	@Autowired
    private UserService userService;
	
	@Autowired
    private RoleService roleService;
	
	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
    }
    
    @Test
	public void testGetUser() {
    	String mockUsername = "z@z.com";

    	Role testRole = new Role();
		testRole.setRole(RoleName.User.getRole());
		this.roleService.saveRole(testRole);
		
    	User foundUser = new User();
		foundUser.setUsername(mockUsername);
		foundUser.setPassword("123");
		
		this.userService.saveUser(foundUser);
		
		User user = (User) this.userService.findUserByUsername(mockUsername).getData();
		
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
	public void testDeleteUser() {
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

	/*@Test
	public void testSaveUser() {
		fail("Not yet implemented");
	}*/

}
