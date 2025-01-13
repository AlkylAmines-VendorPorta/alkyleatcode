package com.novelerp.appcontext.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.dao.LoginDao;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.LoginService;
import com.novelerp.appcontext.service.RoleService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.exception.CustomException;
import com.novelerp.core.util.AuthManager;

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthManager authManager;
	
	@Override
	public UserDto validateLogin(String email, String password) {		
		//List<UserDto> userList = userService.find(" c.email = :email AND c.password= :password", getLoginParams(email, password), null);
		List<UserDto> userList = userService.find("c.email = :email AND (c.isUserDeleted<>'Y' OR c.isUserDeleted is null)", getLoginParams(email, password), null);
		if(CommonUtil.isListEmpty(userList)){
			UserDto user =  new UserDto();
			ResponseDto response=new ResponseDto(true, "No User Found with Email '"+email+"'");
			user.setResponse(response);
			return user;
		}
		if(userList.size()>1){
			UserDto user =  new UserDto();
			ResponseDto response=new ResponseDto(true, "More Than one User Found with Email '"+email+"'");
			user.setResponse(response);
			return user;
		}
		if(!authManager.check(password, userList.get(0).getPassword())){
			UserDto user =  new UserDto();
			ResponseDto response=new ResponseDto(true, "Invalid Password for '"+email+"'");
			user.setResponse(response);
			return user;

		}
		UserDto user = userList.get(0);
		/*List<RoleDto> userRoleList = roleService.getAllUserRoles(user.getUserId());*/
		
		List<RoleDto> roleList =  roleService.getUserRoles(user.getUserId());
		Set<RoleDto> roleSet =  new HashSet<>();
		roleSet.addAll(roleList);
		user.setRoles(roleSet);
		/*user.setRolesList(userRoleList);*/
		ResponseDto response=new ResponseDto(false, "");
		user.setResponse(response);
		return user;
	}
	
	public boolean validatePassword(String password, String encyPassword){
		return authManager.check(password, encyPassword);
	}

	@Override
	public String getRole(int roleId) {
		log.info("Inside LoginServiceImple getRole()");
		return loginDao.getRole(roleId);
	}

	@Override
	public User getUserDetails(String mailID, String passWord) 
	{
		log.debug("<--Inside LoginServiceImpl getUserDetails()");
		return loginDao.getUserDetails(mailID,passWord);
	}


	/**
	 * get login param map
	 * @param email
	 * @param password
	 * @return Map<String, Object> with login request params
	 */
	private Map<String, Object> getLoginParams(String email, String password){
		Map< String, Object> paraMap = new HashMap<>();
		paraMap.put("email", email);
		//paraMap.put("password", password);
		return paraMap;
	}
	@Override
	public UserDto getUserByUserName(String email) {
//		try {
		if(email!=null){
			List<UserDto> userList = userService.find("((LOWER(c.email) = :email AND c.isEmailLogin='Y') OR LOWER(c.userName)= :email) AND (c.isUserDeleted<>'Y' OR c.isUserDeleted is null)", getLoginParams(email.toLowerCase().trim(), null), null);
			if(CommonUtil.isListEmpty(userList)){
				return null;
			}
			if(userList.size()>1){
				return null;
			}
			
			System.out.println("getUserByUserName userList data " +userList);
			UserDto user = userList.get(0);
			List<RoleDto> userRoleList = roleService.getAllUserRoles(user.getUserId());
			List<RoleDto> roleList =  roleService.getUserRoles(user.getUserId());
			Set<RoleDto> roleSet =  new HashSet<>();
			roleSet.addAll(roleList);
			user.setRoles(roleSet);
			user.setRolesList(userRoleList);
			System.out.println("getUserByUserName user data " +user);
			return user;
		}
//		}
//		catch (Exception e) {
//			log.info("Incorrect User",e);
//			System.out.println("Incorrect User" + e.getMessage());
//			throw new CustomException(e.getMessage());
//		}
		return null;
	}
	
}
