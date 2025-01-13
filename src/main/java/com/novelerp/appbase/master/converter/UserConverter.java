package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class UserConverter extends CustomDozerConverter<User, UserDto> implements ObjectConverter<User, UserDto>{

	@Override
	public UserDto convertEntityToDto(User entity, Class<UserDto> dto) {
		UserDto userDto = new UserDto();
		userDto.setUserId(entity.getUserId());
/*		userDto.setValue(entity.getValue());
		userDto.setUserName(entity.getEmail());
		userDto.setName(entity.getName());
		userDto.setDescription(entity.getDescription());
		userDto.setEmail(entity.getEmail());
		userDto.setFax(entity.getFax());*/
		return userDto;
	}
}
