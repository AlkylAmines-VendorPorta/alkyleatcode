package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerItemManufacturerDto;
import com.novelerp.appbase.master.entity.PartnerItemManufacturer;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;

@Component
public class PartnerItemManufacturerConverterPlain extends CustomContextDozerConverter<PartnerItemManufacturer, PartnerItemManufacturerDto>{

	@Override
	public PartnerItemManufacturerDto convertEntityToDto(PartnerItemManufacturer entity,
			Class<PartnerItemManufacturerDto> dtoClass) {

		if(entity ==null){
			return null;
		}
		
		PartnerItemManufacturerDto dto =  new PartnerItemManufacturerDto();
		dto.setPartnerItemManufacturerId(entity.getPartnerItemManufacturerId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setTelephone1(entity.getTelephone1());
		dto.setTelephone2(entity.getTelephone2());
		dto.setFax1(entity.getFax1());
		dto.setFax2(entity.getFax2());
		dto.setMobileNo(entity.getMobileNo());
		return dto;
	}

	
}
