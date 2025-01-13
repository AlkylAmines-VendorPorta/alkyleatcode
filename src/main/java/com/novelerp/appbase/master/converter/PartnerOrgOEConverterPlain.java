package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgOEDto;
import com.novelerp.appbase.master.entity.PartnerOrgOE;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerOrgOEConverterPlain extends CustomDozerConverter<PartnerOrgOE, PartnerOrgOEDto> implements ObjectConverter<PartnerOrgOE, PartnerOrgOEDto>{

	@Override
	public PartnerOrgOEDto convertEntityToDto(PartnerOrgOE entity,
			Class<PartnerOrgOEDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgOEDto dto =  new PartnerOrgOEDto();
		dto.setPartnerOrgOEId(entity.getPartnerOrgOEId());
		dto.setOeType(entity.getOeType());
		dto.setIsNotApplicable(entity.getIsNotApplicable());
		dto.setAuthority(entity.getAuthority());
		dto.setValidFrom(entity.getValidFrom());
		dto.setValidTo(entity.getValidTo());
		dto.setRegsNo(entity.getRegsNo());
		return dto;
	}
}