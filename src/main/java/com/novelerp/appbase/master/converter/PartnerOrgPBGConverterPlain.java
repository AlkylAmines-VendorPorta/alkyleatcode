package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgPBGDto;
import com.novelerp.appbase.master.entity.PartnerOrgPBG;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgPBGConverterPlain extends CustomContextDozerConverter<PartnerOrgPBG, PartnerOrgPBGDto> implements ObjectConverter<PartnerOrgPBG, PartnerOrgPBGDto>{
	
	@Override
	public PartnerOrgPBGDto convertEntityToDto(PartnerOrgPBG entity,
			Class<PartnerOrgPBGDto> dtoClass) {
		
		if (entity == null){
			return null;
		}
		PartnerOrgPBGDto dto = new PartnerOrgPBGDto();
		dto.setPartnerOrgPbgId(entity.getPartnerOrgPbgId());
		dto.setBankGuaranteeNo(entity.getBankGuaranteeNo());
		dto.setIsNotApplicable(entity.getIsNotApplicable());
		dto.setIssueDate(entity.getIssueDate());
		dto.setValidityDate(entity.getValidityDate());
		dto.setPbgAmount(entity.getPbgAmount());
		return dto;
	}

}
