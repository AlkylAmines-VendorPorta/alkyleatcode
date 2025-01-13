package com.novelerp.appcontext.service;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;

public interface LoginService 
{
	public UserDto validateLogin(String userEmail, String passWord);
	public User getUserDetails(String mail, String passWord);
	public String getRole(int roleId);
	public UserDto getUserByUserName(String email);
}