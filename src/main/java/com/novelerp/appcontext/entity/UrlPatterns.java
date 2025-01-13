package com.novelerp.appcontext.entity;

import java.util.List;

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
@Table(name = "m_url_pattern")
public class UrlPatterns extends ContextPO {
	/**
	 * @author Akshay
	 */
	private static final long serialVersionUID = 1L;
	private Long urlPattternId;
	private String value;
	private List<RoleUrlPatterns> roleUrlPatterns;

	@Id
	@SequenceGenerator(name = "m_url_pattern_seq", sequenceName = "m_url_pattern_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_url_pattern_seq")
	@Column(name = "m_url_pattern_id", updatable = false)
	public Long getUrlPattternId() {
		return urlPattternId;
	}

	public void setUrlPattternId(Long urlPattternId) {
		this.urlPattternId = urlPattternId;
	}

	@Column(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@OneToMany(mappedBy = "urlPatterns", fetch = FetchType.EAGER)
	public List<RoleUrlPatterns> getRoleUrlPatterns() {
		return roleUrlPatterns;
	}

	public void setRoleUrlPatterns(List<RoleUrlPatterns> roleUrlPatterns) {
		this.roleUrlPatterns = roleUrlPatterns;
	}

}
