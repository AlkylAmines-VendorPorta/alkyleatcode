package com.novelerp.appbase.master.converter;


import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgLicenseDto;
import com.novelerp.appbase.master.entity.PartnerOrgLicense;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerOrgLicenseConverterPlain extends CustomContextDozerConverter<PartnerOrgLicense, PartnerOrgLicenseDto> implements ObjectConverter<PartnerOrgLicense, PartnerOrgLicenseDto>{

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
		return dto;
	}

}
