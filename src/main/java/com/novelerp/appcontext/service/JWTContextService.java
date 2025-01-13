package com.novelerp.appcontext.service;

import com.novelerp.appcontext.dto.ContextDto;
import com.novelerp.appcontext.dto.UserDto;

public interface JWTContextService {

	public ContextDto getContext();
	void contextInitializer(String jwt, UserDto userDto);


}
