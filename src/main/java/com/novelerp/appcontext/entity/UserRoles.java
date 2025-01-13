package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Entity
@Table(name = "ad_user_roles")
public class UserRoles extends ContextPO {
	private static final long serialVersionUID = 1L;

	private Long userRolesId;
	private String homePagePath; /*
									 * Added for source path for File path for home
									 * page
									 */
	private User user;
	private Role role;
	private String isDefault;

	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "ad_user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "ad_role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "page_Path")
	public String getHomePagePath() {
		return homePagePath;
	}

	public void setHomePagePath(String homePagePath) {
		this.homePagePath = homePagePath;
	}

	@Id
	@SequenceGenerator(name = "ad_user_roles_seq", sequenceName = "ad_user_roles_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_user_roles_seq")
	@Column(name = "ad_user_roles_id", updatable = false)

	public Long getUserRolesId() {
		return userRolesId;
	}

	public void setUserRolesId(Long userRolesId) {
		this.userRolesId = userRolesId;
	}

	@Column(name = "is_default")
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	
}
