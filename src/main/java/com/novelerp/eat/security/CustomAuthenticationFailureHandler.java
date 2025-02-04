package com.novelerp.eat.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

private ObjectMapper objectMapper = new ObjectMapper();

	
@Override
public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
	
		/*response.setStatus(HttpStatus.UNAUTHORIZED.value());
		Map<String, Object> data = new HashMap<>();
		data.put("timestamp",Calendar.getInstance().getTime());
		data.put("exception", exception.getMessage());
		response.getOutputStream().println(objectMapper.writeValueAsString(data));*/
	if(exception.getMessage().equalsIgnoreCase("blocked")){
		response.sendRedirect("./?block");
	}else{
		response.sendRedirect("./?error");
	}

 }
}
