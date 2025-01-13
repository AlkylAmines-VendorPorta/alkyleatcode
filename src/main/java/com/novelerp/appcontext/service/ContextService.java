package com.novelerp.appcontext.service;

import java.util.Set;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.eat.dto.TAHDRDto;

public interface ContextService 
{
	/**
	 * Initialize context, load context dto for the user 
	 * @return
	 */
	public void contextInitializer(UserDto userDto);
	public ContextDto getContext();
	public Set<RoleDto> getRoles();
	public UserDto getUser();
	public BPartnerDto getPartner();
	public UserDetailsDto getUserDetails();
	public RoleDto getDefaultRole();
	public void setSFTPRequiredInfo(BPartnerDto dto,String docType,TAHDRDto tahdr);
	public void setRoles(Set<RoleDto> roleSet);
	
}
