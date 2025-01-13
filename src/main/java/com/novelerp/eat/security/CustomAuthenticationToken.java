package com.novelerp.eat.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.novelerp.appcontext.dto.UserDto;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String captcha;

	public CustomAuthenticationToken(String principal, String credentials, String captcha) {
		super(principal, credentials);
		this.captcha = captcha;
	}

	public CustomAuthenticationToken(UserDto principal, String credentials,
			Collection<? extends GrantedAuthority> authorities, String captcha) {
		super(principal, credentials, authorities);
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}

}
