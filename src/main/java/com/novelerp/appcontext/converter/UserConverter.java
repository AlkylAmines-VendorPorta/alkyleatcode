package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.converter.ObjectConverter;

@Component("USER_CONVERTER")
public class UserConverter extends CustomContextDozerConverter<User, UserDto> implements ObjectConverter<User, UserDto>{

	@Override
	public UserDto convertEntityToDto(User entity, Class<UserDto> dto) {
		if(entity==null){
			return null;
		}
		entity.setLoadDefault(false);
		UserDto userDto = new UserDto();
		userDto.setUserId(entity.getUserId());
		userDto.setEmail(entity.getEmail());
		userDto.setName(entity.getName());
		userDto.setIsActive(entity.getIsActive());
		return userDto;
	}
}
