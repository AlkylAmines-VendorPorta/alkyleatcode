package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.RegistrationTypeDto;
import com.novelerp.appbase.master.entity.RegistrationType;
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
public class RegistrationTypeConverter extends CustomDozerConverter<RegistrationType, RegistrationTypeDto> implements ObjectConverter<RegistrationType, RegistrationTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public RegistrationTypeDto convertEntityToDto(RegistrationType entity, Class<RegistrationTypeDto> dto) {
		if(entity == null){
			return null;
		}
		RegistrationTypeDto RegistrationTypeDto = new RegistrationTypeDto();
		RegistrationTypeDto.setCode(entity.getCode());
		RegistrationTypeDto.setName(entity.getName());
		RegistrationTypeDto.setRegistrationTypeId(entity.getRegistrationTypeId());
		RegistrationTypeDto.setCreated(entity.getCreated());
		RegistrationTypeDto.setUpdated(entity.getUpdated());
		RegistrationTypeDto.setIsActive(entity.getIsActive());
		RegistrationTypeDto.setDescription(entity.getDescription());
		/*RegistrationTypeDto.setPartner(getParter(entity));*/
		
		return RegistrationTypeDto;
	}
	
	private BPartnerDto getParter(RegistrationType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}
