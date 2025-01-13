package com.novelerp.appcontext.service;

import java.util.List;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.entity.UserRoles;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Vivek Birdi
 *
 */
public interface UserRolesService extends CommonService<UserRoles, UserRolesDto>{
	
	public boolean addUserRole(UserDto user, RoleDto role);
	public UserRolesDto getUserRolesByUserId(Long UserId);
	public List<UserRolesDto> getUserRoles(String locationType,String officeLocation, BPartnerDto bpartnerdto);
	public UserRolesDto updateUserRole(UserRolesDto userRoleDto);
	public UserRolesDto addUserRole(UserRolesDto userRolesDto) ;
	public List<UserRolesDto> getSearchedUserList(String litral);
	public UserRolesDto addMultipleUserRole(UserRolesDto userRolesDto);
	
}
