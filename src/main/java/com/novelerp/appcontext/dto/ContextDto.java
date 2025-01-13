package com.novelerp.appcontext.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ContextDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2240234043962820810L;
	private UserDto userDto;
	private Set<RoleDto> roles;
	private BPartnerDto partnerDto;
	private UserDetailsDto userDetailsDto;
	private List<RoleDto> rolesList;
	
	public ContextDto(UserDto userDto) 
	{
		this.userDto = userDto;
		this.roles = userDto.getRoles();
		this.partnerDto =  userDto.getPartner();
		this.userDetailsDto = userDto.getUserDetails();
		this.rolesList=userDto.getRolesList();
	}
	
	/*public ContextDto(UserDto userDto,UserSessionDto userSession) 
	{
		this.userDto = userDto;
		this.roles = userDto.getRoles();
		this.partnerDto =  userDto.getPartner();
		this.userDetailsDto = userDto.getUserDetails();
		this.setUserSessionDto(userSession);
	}*/
	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public BPartnerDto getPartnerDto() {
		return partnerDto;
	}
	public void setPartnerDto(BPartnerDto partnerDto) {
		this.partnerDto = partnerDto;
	}
	public UserDetailsDto getUserDetailsDto() {
		return userDetailsDto;
	}
	public void setUserDetailsDto(UserDetailsDto userDetailsDto) {
		this.userDetailsDto = userDetailsDto;
	}

	public List<RoleDto> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<RoleDto> rolesList) {
		this.rolesList = rolesList;
	}
	

}
