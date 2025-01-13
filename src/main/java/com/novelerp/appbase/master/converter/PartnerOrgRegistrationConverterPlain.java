package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgRegistrationDto;
import com.novelerp.appbase.master.entity.PartnerOrgRegistration;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerOrgRegistrationConverterPlain extends CustomDozerConverter<PartnerOrgRegistration, PartnerOrgRegistrationDto> implements ObjectConverter<PartnerOrgRegistration, PartnerOrgRegistrationDto>{
	@Override
	public PartnerOrgRegistrationDto convertEntityToDto(PartnerOrgRegistration entity,
			Class<PartnerOrgRegistrationDto> dtoClass) {
		if(entity == null){
			return null;
		}
		
		PartnerOrgRegistrationDto dto =  new PartnerOrgRegistrationDto();
		dto.setPartnerOrgRegistrationId(entity.getPartnerOrgRegistrationId());
		dto.setIsApplicable(entity.getIsApplicable());
		dto.setRegistrationAuthority(entity.getRegistrationAuthority());
		dto.setRegistrationNo(entity.getRegistrationNo());
		dto.setIssueDate(entity.getIssueDate());
		dto.setValidityDate(entity.getValidityDate());
		dto.setProductCommencement(entity.getProductCommencement());
		dto.setRegistrationType(entity.getRegistrationType());
		return dto;
	}

}