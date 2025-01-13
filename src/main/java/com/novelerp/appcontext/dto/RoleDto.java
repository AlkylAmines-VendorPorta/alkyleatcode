package com.novelerp.appcontext.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class RoleDto extends CommonContextDto {

	private static final long serialVersionUID = 3439275044877338225L;
	private Long roleId;
	private String name;
	private String value;
	private String description;
	private String userLevel;
	private String pageAccess;
	private String isAdmin;
	private String viewName;
	private String isDefault;
	
	
	private Set<UserDto> users;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getPageAccess() {
		return pageAccess;
	}

	public void setPageAccess(String pageAccess) {
		this.pageAccess = pageAccess;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	@JsonIgnore
	public Set<UserDto> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "RoleDto [roleId=" + roleId + ", name=" + name + ", value=" + value + ", description=" + description
				+ ", userLevel=" + userLevel + ", pageAccess=" + pageAccess + ", isAdmin=" + isAdmin + ", viewName="
				+ viewName + ", isDefault=" + isDefault + ", users=" + users + "]";
	}
	
	

}
