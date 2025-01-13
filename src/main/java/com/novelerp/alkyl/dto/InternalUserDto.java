package com.novelerp.alkyl.dto;

import java.util.List;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserRolesDto;

public class InternalUserDto extends CommonContextDto {

	private static final long serialVersionUID = 2980251901767864437L;
	private List<UserRolesDto> userRolesDtos;

	public List<UserRolesDto> getUserRolesDtos() {
		return userRolesDtos;
	}

	public void setUserRolesDtos(List<UserRolesDto> userRolesDtos) {
		this.userRolesDtos = userRolesDtos;
	}
	
}
