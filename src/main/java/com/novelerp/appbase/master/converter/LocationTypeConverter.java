package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.entity.LocationType;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class LocationTypeConverter extends CustomDozerConverter<LocationType, LocationTypeDto> implements ObjectConverter<LocationType, LocationTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public LocationTypeDto convertEntityToDto(LocationType entity, Class<LocationTypeDto> dto) {
		if(entity == null){
			return null;
		}
		LocationTypeDto LocationTypeDto = new LocationTypeDto();
		LocationTypeDto.setLocationTypeId(entity.getLocationTypeId());
		LocationTypeDto.setName(entity.getName());
		LocationTypeDto.setCreated(entity.getCreated());
		LocationTypeDto.setUpdated(entity.getUpdated());
		LocationTypeDto.setIsActive(entity.getIsActive());
		LocationTypeDto.setLevels(entity.getLevels());
		/*LocationTypeDto.setPartner(getParter(entity));*/
		
		return LocationTypeDto;
	}
	
	private BPartnerDto getParter(LocationType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}

