package com.novelerp.appbase.master.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appbase.master.dto.ReferenceDto;
import com.novelerp.appbase.master.entity.Reference;
import com.novelerp.core.service.CommonService;

public interface ReferenceService extends CommonService<Reference,ReferenceDto>
{
	
	/**
	 * Get role map from Database
	 * @return Map<String,RoleDto> -  HashMap of roles
	 */
	public Map<String, ReferenceDto> getReferenceMap();
	/**
	 * Get list of Roles for userID
	 * @param userID
	 * @return List<Role>
	 */
	public List<ReferenceDto> getReference(Long userID);
	
	public List<ReferenceDto> getAllReferences(String where);
	
	
	/**
	 * Get list of Roles for userID
	 * @param userID
	 * @return List<Role>
	 */
	public List<ReferenceDto> getUserReferences(Long userID);
	
	public ReferenceDto getReferenceById(Long referenceId);// added by aman(for role master)
	
	public boolean deleteReference(Long referenceId);

	

}