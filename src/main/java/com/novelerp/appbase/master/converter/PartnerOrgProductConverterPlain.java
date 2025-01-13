package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerOrgProductDto;
import com.novelerp.appbase.master.entity.PartnerOrgProduct;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
@Component
public class PartnerOrgProductConverterPlain extends CustomContextDozerConverter<PartnerOrgProduct, PartnerOrgProductDto> {
	
	@Override
	public PartnerOrgProductDto convertEntityToDto(PartnerOrgProduct entity, Class<PartnerOrgProductDto> dtoClass) {
		if(entity == null){
			return null;
		}
		PartnerOrgProductDto dto =  new PartnerOrgProductDto();
		dto.setPartnerOrgProductId(entity.getPartnerOrgProductId());
		dto.setLicenceNo(entity.getLicenceNo());
		dto.setQtyManufacturedPM(entity.getQtyManufacturedPM());
		dto.setTurnOver(entity.getTurnOver());
		dto.setLoadDefault(true);
		return dto;
	}
	
}
