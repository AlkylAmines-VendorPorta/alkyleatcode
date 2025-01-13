package com.novelerp.appcontext.dao;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.dao.CommonDao;

public interface LoginDao extends CommonDao<User,UserDto>
{
	public String validateLogin(String userEmail, String passWord);
	public String getRole(int roleId);
	public User getUserDetails(String mail,String passWord);
}