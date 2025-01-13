package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgCertificationDto;
import com.novelerp.appbase.master.entity.PartnerOrgCertification;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerOrgCertificationConverterPlain extends CustomContextDozerConverter<PartnerOrgCertification, PartnerOrgCertificationDto> implements ObjectConverter<PartnerOrgCertification, PartnerOrgCertificationDto>{

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
		return dto;
	}
}
