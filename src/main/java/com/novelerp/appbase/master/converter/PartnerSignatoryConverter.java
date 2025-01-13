package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerSignatoryDto;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.appcontext.converter.LocationConverter;
import com.novelerp.appcontext.converter.UserDetailsConverter;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author {Aman / Vivek Birdi}
 *
 */
@Component
public class PartnerSignatoryConverter extends CustomContextDozerConverter<PartnerSignatory, PartnerSignatoryDto> implements ObjectConverter<PartnerSignatory, PartnerSignatoryDto>{
	
	@Autowired
	private LocationConverter locationConverter;

	@Autowired
	private UserDetailsConverter userDetailsConverter;

	@Override
	public PartnerSignatoryDto convertEntityToDto(PartnerSignatory entity,
			Class<PartnerSignatoryDto> dtoClass) {
		if(entity==null){
			return null;
		}
		PartnerSignatoryDto dto =  new PartnerSignatoryDto();
		dto.setPartnerSignatoryId(entity.getPartnerSignatoryId());
		UserDetails userDetail = entity.getUserDetail();
		userDetail.setLocation(null);
		UserDetailsDto userDetailDto =  userDetailsConverter.getDtoFromEntity(userDetail, UserDetailsDto.class);
		LocationDto location = locationConverter.getDtoFromEntity(entity.getLocation(), LocationDto.class);
		
		dto.setUserDetail(userDetailDto);
		dto.setLocation(location);
		dto.setValidFrom(entity.getValidFrom());
		dto.setLoadDefault(true);
		return dto;
	}
	
	

}
