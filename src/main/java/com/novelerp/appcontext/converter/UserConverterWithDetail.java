package com.novelerp.appcontext.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.converter.ObjectConverter;

@Component("USER_CONVERTER_WITH_DETAILS")
public class UserConverterWithDetail extends CustomContextDozerConverter<User, UserDto> implements ObjectConverter<User, UserDto>{

	@Autowired
	private UserDetailConverterPlain userDetailsConv;

	@Autowired
	private PartnerConverter partnerConverter;
	@Override
	public UserDto convertEntityToDto(User entity, Class<UserDto> dtoClass) {
		if(entity==null){
			return null;
		}
		UserDto userDto = new UserDto();
		userDto.setUserId(entity.getUserId());
		userDto.setEmail(entity.getEmail());
		userDto.setName(entity.getName());
		userDto.setIsActive(entity.getIsActive());
		UserDetails userDetails = entity.getUserDetails();
		UserDetailsDto usersDetailsDto=userDetailsConv.getDtoFromEntity(userDetails, UserDetailsDto.class);
		BPartnerDto partnerDto = partnerConverter.getDtoFromEntity(entity.getPartner(), BPartnerDto.class);
		userDto.setPartner(partnerDto);
		userDto.setUserDetails(usersDetailsDto);
		return userDto;
	} 

}
