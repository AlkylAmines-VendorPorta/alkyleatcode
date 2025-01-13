package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class LocationConverterPlain extends CustomContextDozerConverter<Location, LocationDto> implements ObjectConverter<Location, LocationDto>{

	@Override
	public LocationDto convertEntityToDto(Location entity, Class<LocationDto> dtoClass) {
		if(entity == null){
			return null;
		}
		LocationDto locationDto = new LocationDto();
		locationDto.setLocationId(entity.getLocationId());
		locationDto.setAddress1(entity.getAddress1());
		locationDto.setAddress2(entity.getAddress2());
		locationDto.setPostal(entity.getPostal());
		locationDto.setCity(entity.getCity());
		return locationDto;

	}

}
