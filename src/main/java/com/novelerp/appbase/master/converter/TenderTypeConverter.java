package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.TenderTypeDto;
import com.novelerp.appbase.master.entity.TenderType;
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
public class TenderTypeConverter extends CustomDozerConverter<TenderType, TenderTypeDto> implements ObjectConverter<TenderType, TenderTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public TenderTypeDto convertEntityToDto(TenderType entity, Class<TenderTypeDto> dto) {
		if(entity == null){
			return null;
		}
		TenderTypeDto TenderTypeDto = new TenderTypeDto();
		TenderTypeDto.setCode(entity.getCode());
		TenderTypeDto.setName(entity.getName());
		TenderTypeDto.setTenderTypeId(entity.getTenderTypeId());
		TenderTypeDto.setCreated(entity.getCreated());
		TenderTypeDto.setUpdated(entity.getUpdated());
		TenderTypeDto.setIsActive(entity.getIsActive());
		TenderTypeDto.setDescription(entity.getDescription());
		/*TenderTypeDto.setPartner(getParter(entity));*/
		
		return TenderTypeDto;
	}
	
	private BPartnerDto getParter(TenderType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}
