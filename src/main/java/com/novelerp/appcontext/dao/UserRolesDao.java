package com.novelerp.appcontext.dao;

import java.util.List;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.entity.UserRoles;
import com.novelerp.core.dao.CommonDao;

public interface UserRolesDao extends CommonDao<UserRoles, UserRolesDto>{
	
	public List<UserRoles> getUserRolesList(String locationType,String officeLocation, BPartnerDto bdto);
	public List<UserRoles> getUserRolesByUserId(Long userId);
	public List<UserRoles> searchUserRoles(String litral);
	


}
