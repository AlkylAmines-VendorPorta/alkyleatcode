package com.novelerp.eat.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public CustomAuthenticationFilter() {

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = super.obtainUsername(request);
		String password = super.obtainPassword(request);
		String captcha = request.getParameter("captcha");
		HttpSession session = request.getSession(true);
		String storedCaptcha = (String) session.getAttribute("CAPTCHA");

		if (storedCaptcha == null || !storedCaptcha.equals(request.getParameter("captcha"))) {
			throw new AuthenticationServiceException("Invalid Captcha");
		}

		CustomAuthenticationToken token = new CustomAuthenticationToken(username, password, captcha);
		super.setDetails(request, token);

		return this.getAuthenticationManager().authenticate(token);
	}

}
