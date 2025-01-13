package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.entity.PartnerOrgBIS;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author {Vivek Birdi}
 *
 */
@Component
public class PartnerOrgBISConverterPlain extends CustomDozerConverter<PartnerOrgBIS, PartnerOrgBISDto> implements ObjectConverter<PartnerOrgBIS, PartnerOrgBISDto>{
	
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
		return dto;
	}
}
