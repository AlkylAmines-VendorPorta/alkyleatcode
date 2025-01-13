package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgUserDto;
import com.novelerp.appbase.master.entity.PartnerOrgUser;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.appcontext.converter.LocationConverter;
import com.novelerp.appcontext.converter.UserDetailsConverter;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgUserConverter extends CustomContextDozerConverter<PartnerOrgUser, PartnerOrgUserDto> implements ObjectConverter<PartnerOrgUser, PartnerOrgUserDto>{

	@Autowired
	private UserDetailsConverter userDetailConverter;
	
	@Autowired
	private LocationConverter locationConverter;

	@Autowired
	private PartnerOrgConverter partnerOrgConverter; 
	
	/*Implemented By Vivek Birdi*/
	@Override
	public PartnerOrgUserDto convertEntityToDto(PartnerOrgUser entity,
			Class<PartnerOrgUserDto> dtoClass) {
		PartnerOrgUserDto dto = new PartnerOrgUserDto();
		dto.setPartnerOrgUserId(entity.getPartnerOrgUserId());
		UserDetailsDto userDetails = userDetailConverter.getDtoFromEntity(entity.getUserDetail(), UserDetailsDto.class);
		LocationDto locationDto = locationConverter.getDtoFromEntity(entity.getUserDetail().getLocation(), LocationDto.class);
		PartnerOrgDto partnerOrg = partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		userDetails.setLocation(locationDto);
		dto.setUserDetail(userDetails);
		dto.setPartnerOrg(partnerOrg);
		dto.setEeComment(entity.getEeComment());
		dto.setCeComment(entity.getCeComment());
		dto.setIsEEApproved(entity.getIsEEApproved());
		dto.setIsCEApproved(entity.getIsCEApproved());
		entity.isLoadDefault();
		return dto;
	}

}