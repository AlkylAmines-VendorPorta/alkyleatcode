package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.HolderTypeDto;
import com.novelerp.appbase.master.entity.HolderType;
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
public class HolderTypeConverter extends CustomDozerConverter<HolderType, HolderTypeDto> implements ObjectConverter<HolderType, HolderTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public HolderTypeDto convertEntityToDto(HolderType entity, Class<HolderTypeDto> dto) {
		if(entity == null){
			return null;
		}
		HolderTypeDto holderTypeDto = new HolderTypeDto();
		holderTypeDto.setCode(entity.getCode());
		holderTypeDto.setName(entity.getName());
		holderTypeDto.setHolderTypeId(entity.getHolderTypeId());
		holderTypeDto.setCreated(entity.getCreated());
		holderTypeDto.setUpdated(entity.getUpdated());
		holderTypeDto.setIsActive(entity.getIsActive());
		holderTypeDto.setDescription(entity.getDescription());
		/*HolderTypeDto.setPartner(getParter(entity));*/
		
		return holderTypeDto;
	}
	
	private BPartnerDto getParter(HolderType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}
