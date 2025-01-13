package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerCompanyContactDto;
import com.novelerp.appbase.master.entity.PartnerCompanyContact;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.appcontext.converter.LocationConverter;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman
 *
 */
@Component
public class PartnerCompanyContactConverter extends CustomContextDozerConverter<PartnerCompanyContact, PartnerCompanyContactDto> implements ObjectConverter<PartnerCompanyContact, PartnerCompanyContactDto>{

	@Autowired
	private LocationConverter locationConverter;
	
	@Override
	public PartnerCompanyContactDto convertEntityToDto(PartnerCompanyContact entity,
			Class<PartnerCompanyContactDto> dtoClass) {
		PartnerCompanyContactDto dto =  new PartnerCompanyContactDto();
		dto.setPartnerCompanyContactId(entity.getPartnerCompanyContactId());
		dto.setLocationType(entity.getLocationType());
		entity.setLoadDefault(true);
		LocationDto location =  locationConverter.getDtoFromEntity(entity.getLocation(), LocationDto.class);
		dto.setLocation(location);
		return dto;
	}

}
