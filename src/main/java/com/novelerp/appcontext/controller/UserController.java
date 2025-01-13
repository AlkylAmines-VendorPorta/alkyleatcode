package com.novelerp.appcontext.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/changePlainPassword",method = RequestMethod.GET)
	public @ResponseBody String encrypPlainPasswords(){
		List<UserDto> users = userService.getUsersWithPlainPassword();
		userService.hashPlainPassword(users);
		return "OK";
		
	}
	
	
	

	
	
}
