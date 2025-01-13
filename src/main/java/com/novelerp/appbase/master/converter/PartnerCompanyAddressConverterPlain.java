package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerCompanyAddressConverterPlain extends CustomContextDozerConverter<PartnerCompanyAddress, PartnerCompanyAddressDto> implements ObjectConverter<PartnerCompanyAddress, PartnerCompanyAddressDto>{
	
	@Override
	public PartnerCompanyAddressDto convertEntityToDto(PartnerCompanyAddress entity,
			Class<PartnerCompanyAddressDto> dtoClass) {
		if(entity == null){
			return null;
		}
		
		PartnerCompanyAddressDto dto = new PartnerCompanyAddressDto();
		dto.setPartnerCompanyAddressId(entity.getPartnerCompanyAddressId());
		dto.setIsBillToAddress(entity.getIsBillToAddress());
		dto.setIsShipToAddress(entity.getIsShipToAddress());
		return dto;
	}
}
