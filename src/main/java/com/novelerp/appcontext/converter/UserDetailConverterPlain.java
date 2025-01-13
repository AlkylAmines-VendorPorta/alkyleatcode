package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.converter.ObjectConverter;
/**
 * 
 * @author Vivek Birdi
 *
 */

@Component
public class UserDetailConverterPlain extends CustomContextDozerConverter<UserDetails, UserDetailsDto> implements ObjectConverter<UserDetails, UserDetailsDto>{

	@Override
	public UserDetailsDto convertEntityToDto(UserDetails entity, Class<UserDetailsDto> dtoClass) {
		if(entity==null){
			return null;
		}
		UserDetailsDto dto=new UserDetailsDto();
		dto.setUserDetailsId(entity.getUserDetailsId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setMiddleName(entity.getMiddleName());
		dto.setTelephone1(entity.getTelephone1());
		dto.setTelephone2(entity.getTelephone2());
		dto.setMobileNo(entity.getMobileNo());
		dto.setEmail(entity.getEmail());
		dto.setTitle(entity.getTitle());
		dto.setLocationTypeRef(entity.getLocationTypeRef());
		dto.setFax1(entity.getFax1());
		dto.setFax2(entity.getFax2());
		dto.setDob(entity.getDob());
		dto.setGender(entity.getGender());
		return dto;
	}

}
