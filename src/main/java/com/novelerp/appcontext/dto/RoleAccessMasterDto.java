package com.novelerp.appcontext.dto;

import com.novelerp.appbase.master.dto.TileMasterDto;
import com.novelerp.commons.util.CommonUtil;

/** 
 * @author Aman
 *
 */
public class RoleAccessMasterDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long roleAccessMasterId;
	private RoleDto role;
	
	private TileMasterDto tile;
	
	private String viewOnly;
	private String modifyAccess;
	private String deleteAccess;
	
	public Long getRoleAccessMasterId() {
		return roleAccessMasterId;
	}
	public void setRoleAccessMasterId(Long roleAccessMasterId) {
		this.roleAccessMasterId = roleAccessMasterId;
	}
	
	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
	public TileMasterDto getTile() {
		return tile;
	}
	public void setTile(TileMasterDto tile) {
		this.tile = tile;
	}
	public String getViewOnly() {
		if(CommonUtil.isStringEmpty(viewOnly)){
			viewOnly="N";
		}
		return viewOnly;
	}
	public void setViewOnly(String viewOnly) {
		this.viewOnly = viewOnly;
	}
	public String getModifyAccess() {
		if(CommonUtil.isStringEmpty(modifyAccess)){
			modifyAccess="N";
		}

		return modifyAccess;
	}
	public void setModifyAccess(String modifyAccess) {
		this.modifyAccess = modifyAccess;
	}
	public String getDeleteAccess() {
		if(CommonUtil.isStringEmpty(deleteAccess)){
			deleteAccess="N";
		}

		return deleteAccess;
	}
	public void setDeleteAccess(String deleteAccess) {
		this.deleteAccess = deleteAccess;
	}
	
	


	
}
