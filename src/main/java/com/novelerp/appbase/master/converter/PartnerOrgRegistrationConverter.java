package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgRegistrationDto;
import com.novelerp.appbase.master.entity.PartnerOrgRegistration;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgRegistrationConverter extends CustomDozerConverter<PartnerOrgRegistration, PartnerOrgRegistrationDto> implements ObjectConverter<PartnerOrgRegistration, PartnerOrgRegistrationDto>{

	@Autowired
	private PartnerOrgConverterPlain partnerOrgConverter;
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
		PartnerOrgDto partnerOrg = partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		dto.setPartnerOrg(partnerOrg);
		dto.setRegistrationType(entity.getRegistrationType());
		dto.setLoadDefault(true);
		return dto;
	}

}