package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.appcontext.converter.LocationConverter;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman / vivek Birdi
 *
 */
@Component
public class PartnerCompanyAddressConverter extends CustomContextDozerConverter<PartnerCompanyAddress, PartnerCompanyAddressDto> implements ObjectConverter<PartnerCompanyAddress, PartnerCompanyAddressDto>{

	@Autowired
	private LocationConverter locationConverter;
	
	@Override
	public PartnerCompanyAddressDto convertEntityToDto(PartnerCompanyAddress entity,
			Class<PartnerCompanyAddressDto> dtoClass) {
		if(entity == null){
			return null;
		}
		
		PartnerCompanyAddressDto dto = new PartnerCompanyAddressDto();
		dto.setPartnerCompanyAddressId(entity.getPartnerCompanyAddressId());
		dto.setIsBillToAddress(entity.getIsBillToAddress());
		dto.setIsShipToAddress(entity.getIsShipToAddress());
		LocationDto location =  locationConverter.getDtoFromEntity(entity.getLocation(), LocationDto.class);
		dto.setLocation(location);
		dto.setLoadDefault(true);
		return dto;
	}
}
