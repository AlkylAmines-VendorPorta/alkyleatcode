package com.novelerp.appbase.master.dto;

import java.util.List;

import com.novelerp.appcontext.dto.RoleAccessMasterDto;

/** 
 * @author Aman
 *for list of RoleAccessMaster
 */
public class ListResponseDto {
	
	private List<RoleAccessMasterDto> roleList;

	public List<RoleAccessMasterDto> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleAccessMasterDto> roleList) {
		this.roleList = roleList;
	}
	
	

}
