package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgCertificationDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.entity.PartnerOrgCertification;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman/ Vivek Birdi
 *
 */
@Component
public class PartnerOrgCertificationConverter extends CustomContextDozerConverter<PartnerOrgCertification, PartnerOrgCertificationDto> implements ObjectConverter<PartnerOrgCertification, PartnerOrgCertificationDto>{

	@Autowired
	private PartnerOrgConverterPlain partnerOrgConverter;
	
	@Override
	public PartnerOrgCertificationDto convertEntityToDto(PartnerOrgCertification entity,
			Class<PartnerOrgCertificationDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgCertificationDto dto =  new PartnerOrgCertificationDto();
		dto.setPartnerOrgCertificateId(entity.getPartnerOrgCertificateId());
		dto.setIsNotApplicable(entity.getIsNotApplicable());
		dto.setIsoCertificationNo(entity.getIsoCertificationNo());
		dto.setIsoValidityDate(entity.getIsoValidityDate());
		dto.setIsoCertifyingAuthority(entity.getIsoCertifyingAuthority());
		dto.setIsoName(entity.getIsoName());
		dto.setEeComment(entity.getEeComment());
		dto.setCeComment(entity.getCeComment());
		dto.setIsEEApproved(entity.getIsEEApproved());
        dto.setIsCEApproved(entity.getIsCEApproved());
		PartnerOrgDto partnerOrg =  partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		dto.setPartnerOrg(partnerOrg);
		dto.setLoadDefault(true);
		return dto;
	}
}
