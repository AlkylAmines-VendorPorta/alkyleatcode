package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerOrgBIS;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author {Aman/ Vivek Birdi}
 *
 */
@Component
public class PartnerOrgBISConverter extends CustomDozerConverter<PartnerOrgBIS, PartnerOrgBISDto> implements ObjectConverter<PartnerOrgBIS, PartnerOrgBISDto>{

	@Autowired
	private PartnerOrgConverterPlain partnerOrgConverter;
	
	@Override
	public PartnerOrgBISDto convertEntityToDto(PartnerOrgBIS entity,
			Class<PartnerOrgBISDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgBISDto dto =  new PartnerOrgBISDto();
		dto.setPartnerOrgBisId(entity.getPartnerOrgBisId());
		dto.setBisLicenceNo(entity.getBisLicenceNo());
		dto.setValidFrom(entity.getValidFrom());
		dto.setValidTo(entity.getValidTo());
		PartnerOrgDto partnerOrg = partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		dto.setPartnerOrg(partnerOrg);
		dto.setLoadDefault(true);
		return dto;
	}
}
