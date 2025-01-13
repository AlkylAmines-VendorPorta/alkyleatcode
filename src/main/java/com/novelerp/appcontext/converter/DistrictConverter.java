package com.novelerp.appcontext.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.entity.District;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Component
public class DistrictConverter extends CustomContextDozerConverter<District, DistrictDto>{

	@Autowired
	private RegionConverterPlain regionConverter;
	
	@Override
	public DistrictDto convertEntityToDto(District entity, Class<DistrictDto> dtoClass) {
		if(entity == null){
			return null;
		}
		
		DistrictDto dto =  new DistrictDto();
		dto.setDistrictId(entity.getDistrictId());
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		RegionDto region = regionConverter.getDtoFromEntity(entity.getRegion(), RegionDto.class);
		dto.setRegion(region);
		entity.setLoadDefault(true);
		return dto;
	}

}
