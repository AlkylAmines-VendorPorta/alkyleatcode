package com.novelerp.appcontext.service;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.service.CommonService;

public interface RegisterUser extends CommonService<User, UserDto>{

	/**
	 * Register user
	 * @param dto
	 * @return true if successfully registered, false otherwise
	 */
	public UserDto register(UserDto dto);
	
	public BPartnerDto addPartner(UserDto dto);
	
	public boolean addUserRole(UserDto dto);
	
	public boolean addUserRole(UserDto dto,RoleDto role);
}
