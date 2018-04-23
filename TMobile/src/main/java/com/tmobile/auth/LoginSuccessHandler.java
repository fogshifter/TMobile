package com.tmobile.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.tmobile.service.UserService;

//import com.tsystems.provider.dao.UserDAO;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private UserService userService;
	
	@Autowired
	public LoginSuccessHandler(UserService service) {
		this.userService = service;
	}
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//		String email = request.getAttribute("username").toString();
		
		//String url = getRedirectUrl(authentication);
		String url = new String();

        for(GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();

            if(role.contains("ROLE_MANAGER")) {
                url = "/manager";
            }

            else if(role.contains("ROLE_CUSTOMER")) {
                url = "/customer/";
            }
        }
		
		if(!response.isCommitted()) {
			response.sendRedirect(String.join("", request.getContextPath(), url));
		}

	}
	
//	private String getTargetUrl(Authentication auth, String email) {
//		Collection<? extends GrantedAuthority> authoriauth.getAuthorities();
//	private String getRedirectUrl(Authentication auth) {
//
//		String url = new String();
//
//		for(GrantedAuthority authority : auth.getAuthorities()) {
//			String role = authority.getAuthority();
//
//			if(role.contains("ROLE_MANAGER")) {
//				url = "/manager";
//			}
//
//			else if(role.contains("ROLE_CUSTOMER")) {
//				int customerId = userService.getCustomerId(auth.getName());
//				url = String.format("/customer/%d", customerId);
//			}
//		}
//
//		return url;
//	}
}
