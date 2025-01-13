package com.novelerp.appcontext.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class LocationConverter extends CustomContextDozerConverter<Location, LocationDto> implements ObjectConverter<Location, LocationDto>{


	@Autowired
	private CountryConverterPlain countryConverter;
	@Autowired
	private RegionConverterPlain regionConverter;
	
	@Autowired
	private DistrictConverterPlain districtConverter;
	
	@Override
	public LocationDto convertEntityToDto(Location entity, Class<LocationDto> dtoClass) {
		if(entity == null){
			return null;
		}
		LocationDto dto = new LocationDto();
		dto.setLocationId(entity.getLocationId());
		dto.setAddress1(entity.getAddress1());
		dto.setAddress2(entity.getAddress2());
		dto.setCity(entity.getCity());
		dto.setPostal(entity.getPostal());
		CountryDto country = countryConverter.getDtoFromEntity(entity.getCountry(), CountryDto.class);
		RegionDto region  =  regionConverter.getDtoFromEntity(entity.getRegion(), RegionDto.class);
		DistrictDto district =  districtConverter.getDtoFromEntity(entity.getDistrict(), DistrictDto.class);
		
		dto.setCountry(country);
		dto.setRegion(region);
		dto.setDistrict(district);
		entity.setLoadDefault(true);
		return dto;
	}
	
}
