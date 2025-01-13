/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.TechnicalBid;

@Component
public class TechnicalBidConverter extends CustomContextDozerConverter<TechnicalBid, TechnicalBidDto>
		implements ObjectConverter<TechnicalBid, TechnicalBidDto> {

	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.converter.CustomContextDozerConverter#convertEntityToDto(com.novelerp.appcontext.entity.ContextPO, java.lang.Class)
	 */
	@Override
	public TechnicalBidDto convertEntityToDto(TechnicalBid entity, Class<TechnicalBidDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TechnicalBidDto dto= new TechnicalBidDto();
		dto.setTechnicalBidId(entity.getTechnicalBidId());
		dto.setIsSubmit(entity.getIsSubmit());
		return dto;
	}

}
