package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.BidTypeDto;
import com.novelerp.appbase.master.entity.BidType;
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
public class BidTypeConverter extends CustomDozerConverter<BidType, BidTypeDto> implements ObjectConverter<BidType, BidTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public BidTypeDto convertEntityToDto(BidType entity, Class<BidTypeDto> dto) {
		if(entity == null){
			return null;
		}
		BidTypeDto bidTypeDto = new BidTypeDto();
		bidTypeDto.setCode(entity.getCode());
		bidTypeDto.setName(entity.getName());
		bidTypeDto.setBidTypeId(entity.getBidTypeId());
		bidTypeDto.setCreated(entity.getCreated());
		bidTypeDto.setUpdated(entity.getUpdated());
		bidTypeDto.setIsActive(entity.getIsActive());
		bidTypeDto.setDescription(entity.getDescription());
		/*bidTypeDto.setPartner(getParter(entity));*/
		
		return bidTypeDto;
	}
	
	private BPartnerDto getParter(BidType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}

