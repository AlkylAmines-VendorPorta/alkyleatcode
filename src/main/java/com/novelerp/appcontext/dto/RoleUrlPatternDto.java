package com.novelerp.appcontext.dto;

public class RoleUrlPatternDto extends CommonContextDto{

	/**
	 * @author Akshay
	 */
	private static final long serialVersionUID = 1L;
	private Long roleUrlPatternsId;
	private RoleDto roleDto;
	private UrlPatternsDto urlPatternsDto;

	public Long getRoleUrlPatternsId() {
		return roleUrlPatternsId;
	}
	public void setRoleUrlPatternsId(Long roleUrlPatternsId) {
		this.roleUrlPatternsId = roleUrlPatternsId;
	}
	public RoleDto getRoleDto() {
		return roleDto;
	}
	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}
	public UrlPatternsDto getUrlPatternsDto() {
		return urlPatternsDto;
	}
	public void setUrlPatternsDto(UrlPatternsDto urlPatternsDto) {
		this.urlPatternsDto = urlPatternsDto;
	}
	
}
