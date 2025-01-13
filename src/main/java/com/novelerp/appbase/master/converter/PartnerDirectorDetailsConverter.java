package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerDirectorDetailsConverter extends CustomDozerConverter<User, UserDto> implements ObjectConverter<User, UserDto>{

	
	@Override
	public UserDto convertEntityToDto(User entity,
			Class<UserDto> dtoClass) {
		// TODO Auto-generated method stub
		return null;
	}
}
