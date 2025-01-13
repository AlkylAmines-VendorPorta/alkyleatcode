package com.novelerp.appcontext.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.entity.Role;
import com.novelerp.core.service.CommonService;

public interface RoleService extends CommonService<Role, RoleDto>{

	/**
	 * Get role map from Database
	 * @return Map<String,RoleDto> -  HashMap of roles
	 */
	public Map<String, RoleDto> getRoleMap();
	/**
	 * Get list of Roles for userID
	 * @param userID
	 * @return List<Role>
	 */
	public List<RoleDto> getRoles(Long userID);
	
	public List<RoleDto> getAllRoles(String where);
	
	
	/**
	 * Get list of Roles for userID
	 * @param userID
	 * @return List<Role>
	 */
	public List<RoleDto> getUserRoles(Long userID);
	
	public RoleDto getRoleById(Long roleId);// added by aman(for role master)
	public boolean deleteRole(Long id);
	public List<RoleDto> getAllUserRoles(Long userId);
	


}
