package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgUserDto;
import com.novelerp.appbase.master.entity.PartnerOrgUser;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerOrgUserConverterPlain extends CustomContextDozerConverter<PartnerOrgUser, PartnerOrgUserDto> implements ObjectConverter<PartnerOrgUser, PartnerOrgUserDto>{

	@Override
	public PartnerOrgUserDto convertEntityToDto(PartnerOrgUser entity, Class<PartnerOrgUserDto> dtoClass) {
		PartnerOrgUserDto dto =  new PartnerOrgUserDto();
		dto.setPartnerOrgUserId(entity.getPartnerOrgUserId());
		return dto;
	}

	
}