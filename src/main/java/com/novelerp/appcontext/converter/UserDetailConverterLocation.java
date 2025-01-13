package com.novelerp.appcontext.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class UserDetailConverterLocation extends CustomContextDozerConverter<UserDetails, UserDetailsDto> implements ObjectConverter<UserDetails, UserDetailsDto>{

	@Autowired
	private DesignationConverterPlain designationConverter;
	
	@Autowired
	private LocationConverter locationConverter;

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
		dto.setFax1(entity.getFax1());
		dto.setFax2(entity.getFax2());
		dto.setUserDetailType(entity.getUserDetailType());
		DesignationDto designation = designationConverter.getDtoFromEntity(entity.getDesignation(), DesignationDto.class);
		LocationDto location = locationConverter.getDtoFromEntity(entity.getLocation(), LocationDto.class);
		dto.setLocation(location);
		dto.setDesignation(designation);
		dto.setEmail(entity.getEmail());
		dto.setTitle(entity.getTitle());
		dto.setLocationTypeRef(entity.getLocationTypeRef());
		entity.setLoadDefault(true);
		return dto;
	}

}
