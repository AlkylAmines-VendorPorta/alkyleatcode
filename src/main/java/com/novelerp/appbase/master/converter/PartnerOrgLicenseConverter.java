package com.novelerp.appbase.master.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgLicenseDto;
import com.novelerp.appbase.master.entity.PartnerOrgLicense;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgLicenseConverter extends CustomContextDozerConverter<PartnerOrgLicense, PartnerOrgLicenseDto> implements ObjectConverter<PartnerOrgLicense, PartnerOrgLicenseDto>{

	@Autowired
	private PartnerOrgConverterPlain partnerOrgConverter;
	
	@Override
	public PartnerOrgLicenseDto convertEntityToDto(PartnerOrgLicense entity,
			Class<PartnerOrgLicenseDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgLicenseDto dto = new PartnerOrgLicenseDto();
		dto.setPartnerOrgLicenceId(entity.getPartnerOrgLicenceId());
		dto.setLicenceNo(entity.getLicenceNo());
		dto.setLicenceValidityDate(entity.getLicenceValidityDate());
		dto.setLicenseType(entity.getLicenseType());
		PartnerOrgDto partnerOrg =  partnerOrgConverter.getDtoFromEntity(entity.getPartnerOrg(), PartnerOrgDto.class);
		dto.setPartnerOrg(partnerOrg);
		dto.setLoadDefault(true);
		return dto;
	}

}
