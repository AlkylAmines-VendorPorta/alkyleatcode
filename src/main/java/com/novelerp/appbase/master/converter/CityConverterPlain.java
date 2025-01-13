package com.novelerp.appbase.master.converter;

import com.novelerp.appbase.master.dto.CityDto;
import com.novelerp.appbase.master.entity.City;
import com.novelerp.core.converter.CustomDozerConverter;

public class CityConverterPlain extends CustomDozerConverter<City, CityDto>{

	@Override
	public CityDto convertEntityToDto(City entity, Class<CityDto> dtoClass) {
		CityDto dto =  new CityDto();
		dto.setCityId(entity.getCityId());
		dto.setName(entity.getName());
//		dto.set
		return dto;
	}


}
