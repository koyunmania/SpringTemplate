package com.spring.template.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.template.model.User;
import com.spring.template.service.ServiceResult;

@RestController
public class AuthenticationServiceRestController {
	@Autowired
	AuthenticationManager authManager;
	
	@RequestMapping(value = "/api/authuser", 
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	@ResponseBody
	public ServiceResult authUser(@RequestBody User user) {
		ServiceResult serviceResult = new ServiceResult();
		UsernamePasswordAuthenticationToken authReq
	      = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		try { 
			Authentication auth = authManager.authenticate(authReq);
		    SecurityContext sc = SecurityContextHolder.getContext();
		    sc.setAuthentication(auth);
		    //HttpSession session = req.getSession(true);
		    //session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

		    serviceResult.setMessage("Successfully authenticated.");
			serviceResult.setData(user);
			serviceResult.setStatus(true);
		} catch (Exception e){
			serviceResult.setMessage("Authentication error!: " + e.getMessage());
			serviceResult.setData(user);
			serviceResult.setStatus(false);
		}
		return serviceResult;
	}
	
	@RequestMapping(value = "/api/getuser", 
			method = RequestMethod.GET,
			produces = "application/json")
	@ResponseBody
	public ServiceResult getUser() {
		ServiceResult serviceResult = new ServiceResult();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		serviceResult.setMessage(currentPrincipalName);
		return serviceResult;
	}
	
	@RequestMapping(value = "/api/logout", 
			method = RequestMethod.GET,
			produces = "application/json")
	@ResponseBody
	public ServiceResult logout() {
		ServiceResult serviceResult = new ServiceResult();
		try { 
			SecurityContextHolder.clearContext();
			serviceResult.setMessage("Logged out.");
			serviceResult.setData(null);
			serviceResult.setStatus(true);
		} catch (Exception e) {
			serviceResult.setMessage("Logout error!");
			serviceResult.setData(null);
			serviceResult.setStatus(false);
		}

		return serviceResult;
	}
}
