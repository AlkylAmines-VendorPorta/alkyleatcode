package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgOEDto;
import com.novelerp.appbase.master.entity.PartnerOrgOE;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author {Aman/ Vivek Birdi}
 *
 */
@Component
public class PartnerOrgOEConverter extends CustomDozerConverter<PartnerOrgOE, PartnerOrgOEDto> implements ObjectConverter<PartnerOrgOE, PartnerOrgOEDto>{

	@Autowired
	private PartnerOrgConverterPlain partnerOrgConverter;
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
		PartnerOrgDto partnerOrg = partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		dto.setPartnerOrg(partnerOrg);
		dto.setLoadDefault(true);
		dto.setIsApproved(entity.getIsApproved());
		dto.setRemark(entity.getRemark());
		dto.setEeComment(entity.getEeComment());
		dto.setCeComment(entity.getCeComment());
		dto.setIsEEApproved(entity.getIsEEApproved());
		dto.setIsCEApproved(entity.getIsCEApproved());
		return dto;
	}
}