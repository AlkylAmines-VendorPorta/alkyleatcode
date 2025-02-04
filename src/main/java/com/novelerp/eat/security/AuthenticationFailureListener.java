package com.novelerp.eat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>{

	@Autowired
	LoginAttemptService loginAttemptService;
	
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
		
		/*WebAuthenticationDetails auth = (WebAuthenticationDetails) e.getAuthentication().getDetails();
		loginAttemptService.loginFailed(auth.getRemoteAddress());*/
	}

}
