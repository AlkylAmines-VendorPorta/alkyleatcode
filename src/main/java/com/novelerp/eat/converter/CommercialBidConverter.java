/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.entity.CommercialBid;

@Component
public class CommercialBidConverter extends CustomDozerConverter<CommercialBid, CommercialBidDto>
		implements ObjectConverter<CommercialBid, CommercialBidDto> {

	@Override
	public CommercialBidDto convertEntityToDto(CommercialBid entity, Class<CommercialBidDto> dtoClass) {
		if(entity==null){
			return null;
		}
		CommercialBidDto dto=new CommercialBidDto();
		dto.setCommercialBidId(entity.getCommercialBidId());
		dto.setDeliveringMonth(entity.getDeliveringMonth());
		dto.setExciseDuty(entity.getExciseDuty());
		dto.setExciseDutyRate(entity.getExciseDutyRate());
		dto.setFirstLot(entity.getFirstLot());
		return dto;
	}
	
	
	
}
