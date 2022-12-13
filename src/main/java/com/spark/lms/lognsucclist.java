package com.spark.lms;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


import com.spark.lms.service.Userserv;

@Component
public class lognsucclist implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private Userserv usrService;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
        User usr = (User) event.getAuthentication().getPrincipal();
        String displayName = usrService.getByUsername( usr.getUsername() ).getDisplayName();
        httpSession.setAttribute("loggedInUserName", displayName);
	}
	
	@Autowired
	private HttpSession httpSession;
	
	
	
}
