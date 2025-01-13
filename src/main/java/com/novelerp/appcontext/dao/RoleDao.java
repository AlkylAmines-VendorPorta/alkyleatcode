package com.novelerp.appcontext.dao;

import java.util.List;

import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.entity.Role;
import com.novelerp.core.dao.CommonDao;

public interface RoleDao extends CommonDao<Role, RoleDto>{
	
	public List<Role> getUserRoles(Long userID);

	public List<Role> getUserAllRoles(Long userId);
}
