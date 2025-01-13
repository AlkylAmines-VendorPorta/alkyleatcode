package com.novelerp.appcontext.dto;

import java.util.List;

public class UrlPatternsDto extends CommonContextDto {

	private static final long serialVersionUID = 3439275044877338225L;
	private Long urlPattternId;
	private String value;
	private List<RoleUrlPatternDto> roleUrlPatterns;


	public Long getUrlPattternId() {
		return urlPattternId;
	}

	public void setUrlPattternId(Long urlPattternId) {
		this.urlPattternId = urlPattternId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<RoleUrlPatternDto> getRoleUrlPatterns() {
		return roleUrlPatterns;
	}

	public void setRoleUrlPatterns(List<RoleUrlPatternDto> roleUrlPatterns) {
		this.roleUrlPatterns = roleUrlPatterns;
	}
}
