package com.novelerp.eat.security;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.security.UserPrincipal;
import com.novelerp.appcontext.service.LoginService;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private LoginService loginService;
	
	/*@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto userDto  = loginService.getUserByUserName(username);
		
		if(userDto == null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
			return new org.springframework.security.core.userdetails.User(userDto.getEmail(), userDto.getPassword(), 
				 true, true, true, true, getGrantedAuthorities(userDto));
	}*/

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto userDto  = loginService.getUserByUserName(username);
		System.out.println("validating user --> "+username);
		
		if(userDto == null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
			
		}
		return UserPrincipal.create(userDto);
			/*return new org.springframework.security.core.userdetails.User(userDto.getEmail(), userDto.getPassword(), 
				 true, true, true, true, getGrantedAuthorities(userDto));*/
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(UserDto user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(RoleDto role : user.getRoles()){
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getValue()));
		}
		return authorities;
	}
	
}
