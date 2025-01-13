/*File Name :Role.java
*created by :Lakshmi
*Description : This is entity file, used for creating respective database table's column's
*			   Used for users role details
*Database Table dependency : eauction/ad_role			   	
*
*/

package com.novelerp.appcontext.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ad_role")
public class Role extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5114652250037675343L;
	
	private Long roleId;
	private String name;
	private String value;
	private String description;
	private String userLevel;
	private String pageAccess;
	private String isAdmin;
	private String viewName;
/*	private Set<User> users;*/
	
	private Set<UserRoles> userRoles;

	@Id
	@SequenceGenerator(name = "ad_role_seq", sequenceName = "ad_role_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_role_seq")
	@Column(name = "ad_role_id", updatable = false)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name="value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="user_level")
	public String getUserLevel() {
		return userLevel;
	}
	
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	
	@Column(name="page_access")
	public String getPageAccess() {
		return pageAccess;
	}

	public void setPageAccess(String pageAccess) {
		this.pageAccess = pageAccess;
	}
	
	@Column(name="isadmin")
	public String getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Column(name="view_name")
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
/*
	@ManyToMany(mappedBy="roles",fetch=FetchType.LAZY)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
*/

	@OneToMany(fetch=FetchType.LAZY, mappedBy="role")
	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}	
	
	
}
