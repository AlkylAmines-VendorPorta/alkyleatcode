package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.PartnerCompanyDetailsDto;
import com.novelerp.appcontext.entity.PartnerCompanyDetails;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerCompanyDetailsConverter extends CustomDozerConverter<PartnerCompanyDetails, PartnerCompanyDetailsDto> implements ObjectConverter<PartnerCompanyDetails, PartnerCompanyDetailsDto>{

	@Override
	public PartnerCompanyDetailsDto convertEntityToDto(PartnerCompanyDetails entity,
			Class<PartnerCompanyDetailsDto> dtoClass) {
		/* Vivek Birdi .. implementation */
		if(entity==null){
			return null;
		}

		PartnerCompanyDetailsDto dto = new PartnerCompanyDetailsDto();
		dto.setCompanyName(entity.getCompanyName());
		dto.setCompanyType(entity.getCompanyType());
		dto.setContractorType(entity.getContractorType());
		dto.setCrnNo(entity.getCrnNo());
		return dto;
	}

}
