package com.novelerp.appcontext.dto;

import java.util.List;

public class UserRolesDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2980251901767864437L;
	private Long userRolesId;
	private UserDto user;
	private RoleDto role;
	private String isDefault;
	private List<RoleDto> roleList;
	private Long roleId;//for temporaray purpose
	private Long userId;//for temporaray purpose
	
	
	public Long getUserRolesId() {
		return userRolesId;
	}
	public void setUserRolesId(Long userRolesId) {
		this.userRolesId = userRolesId;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public List<RoleDto> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleDto> roleList) {
		this.roleList = roleList;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "UserRolesDto [userRolesId=" + userRolesId + ", user=" + user + ", role=" + role + ", isDefault="
				+ isDefault + ", roleList=" + roleList + ", roleId=" + roleId + ", userId=" + userId + "]";
	}
	
	
	
	

}
