package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgRDAECDto;
import com.novelerp.appbase.master.entity.PartnerOrgRDAEC;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerOrgRDAECConverterPlain extends CustomDozerConverter<PartnerOrgRDAEC, PartnerOrgRDAECDto> implements ObjectConverter<PartnerOrgRDAEC, PartnerOrgRDAECDto>{

	@Override
	public PartnerOrgRDAECDto convertEntityToDto(PartnerOrgRDAEC entity,
			Class<PartnerOrgRDAECDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgRDAECDto dto =  new PartnerOrgRDAECDto();
		dto.setPartnerOrgRDAECId(entity.getPartnerOrgRDAECId());
		dto.setIsApplicable(entity.getIsApplicable());
		dto.setIsPioneer(entity.getIsPioneer());
		dto.setElegibilityType(entity.getElegibilityType());
		dto.setDevelopingRegion(entity.getDevelopingRegion());
		dto.setValidFrom(entity.getValidFrom());
		dto.setValidTo(entity.getValidTo());
		return dto;
	}

}
