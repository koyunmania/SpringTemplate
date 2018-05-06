package com.spring.template.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.template.model.RoleList;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	DataSource dataSource;
	
	@Configuration
	@Order(2)
	public static class UiSecurityConfig extends WebSecurityConfigurerAdapter {

		public UiSecurityConfig() {
	        super();
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .antMatchers("/", "/home", "/register", "/fileupload/**").permitAll()
	                .antMatchers("/admin/**").hasAuthority(RoleList.Admin.getName())
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .and()
	            .csrf().disable()
	            .logout()
	                .permitAll();
	    }
	}

	@Configuration
	@Order(1)
	public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

		public ApiSecurityConfig() {
	        super();
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.antMatcher("/api/*")
            .authorizeRequests()
                .antMatchers("/api/authuser", "/api/register").permitAll()
                .antMatchers("/api/*").hasAuthority(RoleList.ApiUser.getName())
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/api/login")
                .permitAll()
                .and()
            .csrf().disable()
            .logout()
                .permitAll();
	    }
	}

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder)
		.usersByUsernameQuery("select username,password,enabled from user_profile where username=?")
		.authoritiesByUsernameQuery("SELECT username, role FROM user_role \n" + 
				"INNER JOIN user_profile ON user_profile.user_id = user_role.user_id\n" + 
				"INNER JOIN role ON role.role_id = user_role.role_id WHERE user_profile.username=?");    	
    }
	
}
