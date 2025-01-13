package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.entity.Region;

@Component
public class RegionConverterPlain extends CustomContextDozerConverter<Region, RegionDto> {

	@Override
	public RegionDto convertEntityToDto(Region entity, Class<RegionDto> dtoClass) {
		if(entity==null){
			return null;
		}
		RegionDto dto = new RegionDto();
		dto.setRegionId(entity.getRegionId());
		dto.setName(entity.getName());
		return dto;
	}
	
	

}
