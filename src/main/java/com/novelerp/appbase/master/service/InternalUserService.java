package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserRolesDto;

public interface InternalUserService {
	
	public CustomResponseDto createInternalUser(UserRolesDto dto);
	public CustomResponseDto updateInternalUser(UserRolesDto dto);
	public com.novelerp.core.dto.CustomResponseDto saveInteralUserCore(UserRolesDto dto);
	public com.novelerp.core.dto.CustomResponseDto updateInternalUserCore(UserRolesDto dto);
	public com.novelerp.core.dto.CustomResponseDto createNewInvitedUser(UserDto userDto);
}
