package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerItemManufacturerDto;
import com.novelerp.appbase.master.entity.PartnerItemManufacturer;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.appcontext.converter.LocationConverter;
import com.novelerp.appcontext.dto.LocationDto;

@Component
public class PartnerItemManufacturerConverter extends CustomContextDozerConverter<PartnerItemManufacturer, PartnerItemManufacturerDto>{

	@Autowired
	private PartnerItemManufacturerConverterPlain plainConverter;

	@Autowired
	private LocationConverter locationConverter;
	
	@Override
	public PartnerItemManufacturerDto convertEntityToDto(PartnerItemManufacturer entity,
			Class<PartnerItemManufacturerDto> dtoClass) {
		if(entity == null){
			return null;
		}
		
		PartnerItemManufacturerDto dto =  plainConverter.getDtoFromEntity(entity, PartnerItemManufacturerDto.class);
		LocationDto location= locationConverter.getDtoFromEntity(entity.getLocation(), LocationDto.class);
		dto.setLocation(location);
		dto.setLoadDefault(true);
		dto.setEeComment(entity.getEeComment());
		dto.setCeComment(entity.getCeComment());
		dto.setIsCEApproved(entity.getIsCEApproved());
		dto.setIsEEApproved(entity.getIsEEApproved());
		return dto;
	}
	
}
