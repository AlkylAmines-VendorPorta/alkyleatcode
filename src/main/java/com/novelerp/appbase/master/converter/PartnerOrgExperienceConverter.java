package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgExperienceDto;
import com.novelerp.appbase.master.entity.PartnerOrgExperience;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgExperienceConverter extends CustomDozerConverter<PartnerOrgExperience, PartnerOrgExperienceDto> implements ObjectConverter<PartnerOrgExperience, PartnerOrgExperienceDto>{
	
	@Autowired
	private PartnerOrgConverterPlain partnerOrgConverter;
	
	@Override
	public PartnerOrgExperienceDto convertEntityToDto(PartnerOrgExperience entity,
			Class<PartnerOrgExperienceDto> dtoClass) {
		if(entity == null){
			return null;
		}

		PartnerOrgExperienceDto dto  = new PartnerOrgExperienceDto();
		dto.setPartnerOrgExperienceId(entity.getPartnerOrgExperienceId());
		dto.setExpDesignYear(entity.getExpDesignYear());
		dto.setExpDesignMonths(entity.getExpDesignMonths());
		dto.setExpManufacturingYear(entity.getExpManufacturingYear());
		dto.setExpManufacturingMonths(entity.getExpManufacturingMonths());
		dto.setExpSupplyingYear(entity.getExpSupplyingYear());
		dto.setExpSupplyingMonths(entity.getExpSupplyingMonths());
		dto.setExpTestingYear(entity.getExpTestingYear());
		dto.setExpTestingMonths(entity.getExpTestingMonths());
		dto.setEeComment(entity.getEeComment());
		dto.setCeComment(entity.getCeComment());
		dto.setIsEEApproved(entity.getIsEEApproved());
		dto.setIsCEApproved(entity.getIsCEApproved());
		PartnerOrgDto partnerOrg =  partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		dto.setPartnerOrg(partnerOrg);
		entity.setLoadDefault(true);
		return dto;

	}
}
