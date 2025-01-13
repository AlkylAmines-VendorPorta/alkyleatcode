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


@Entity
@Table(name = "ad_role_url_patterns")
public class RoleUrlPatterns extends ContextPO{

	/**
	 * @author Akshay
	 */
	private static final long serialVersionUID = 7991344774735272396L;
	private Long roleUrlPatternsId;
	private Role role;
	private UrlPatterns urlPatterns;
	
	@Id
	@SequenceGenerator(name="ad_role_url_patterns_seq",sequenceName="ad_role_url_patterns_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="ad_role_url_patterns_seq")	
	@Column(name = "ad_role_url_patterns_id", updatable=false)	
	public Long getRoleUrlPatternsId() {
		return roleUrlPatternsId;
	}
	public void setRoleUrlPatternsId(Long roleUrlPatternsId) {
		this.roleUrlPatternsId = roleUrlPatternsId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_url_pattern_id")
	public UrlPatterns getUrlPatterns() {
		return urlPatterns;
	}
	public void setUrlPatterns(UrlPatterns urlPatterns) {
		this.urlPatterns = urlPatterns;
	}
	
}
