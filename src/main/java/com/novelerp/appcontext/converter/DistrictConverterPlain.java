package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.entity.District;

@Component
public class DistrictConverterPlain extends CustomContextDozerConverter<District, DistrictDto> {

	@Override
	public DistrictDto convertEntityToDto(District entity, Class<DistrictDto> dtoClass) {

		if (entity == null){
			return null;
		}
		DistrictDto dto = new DistrictDto();
		dto.setDistrictId(entity.getDistrictId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		return dto;
	}

}
