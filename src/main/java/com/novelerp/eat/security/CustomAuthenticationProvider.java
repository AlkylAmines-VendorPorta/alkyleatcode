package com.novelerp.eat.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.LoginService;
import com.novelerp.core.util.AuthManager;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private LoginService loginService;
	@Autowired
    private LoginAttemptService loginAttemptService;
	@Autowired
    private HttpServletRequest request;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
		
		String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new BadCredentialsException("blocked");
        }
		UserDto user = loginService.getUserByUserName(token.getName());
		if(user == null || !(AuthManager.compare(token.getCredentials().toString(), user.getPassword()))){
			throw new BadCredentialsException("The credentials are invalid");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RoleDto role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getValue()));
		}
		return new CustomAuthenticationToken(user, user.getPassword(), authorities, token.getCaptcha());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	private String getClientIP() {
	    String xfHeader = request.getHeader("X-Forwarded-For");
	    if (xfHeader == null){
	        return request.getRemoteAddr();
	    }
	    return xfHeader.split(",")[0];
	}
}