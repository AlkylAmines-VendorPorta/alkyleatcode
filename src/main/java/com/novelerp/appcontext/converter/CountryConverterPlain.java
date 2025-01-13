package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.entity.Country;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class CountryConverterPlain extends CustomContextDozerConverter<Country, CountryDto> implements ObjectConverter<Country, CountryDto>{

	@Override
	public CountryDto convertEntityToDto(Country entity, Class<CountryDto> dtoClass) {
		if(entity == null){
			return null;
		}
		
		CountryDto dto =  new CountryDto();
		dto.setCountryId(entity.getCountryId());
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		return dto;
	} 
	

}
