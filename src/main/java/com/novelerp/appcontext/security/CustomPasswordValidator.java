package com.novelerp.appcontext.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.novelerp.core.util.AuthManager;

public class CustomPasswordValidator implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return AuthManager.compare(rawPassword.toString(), encodedPassword);
	}

}
