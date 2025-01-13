/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.entity.Bidder;

@Component
public class BidderConverter extends CustomContextDozerConverter<Bidder, BidderDto>
		implements ObjectConverter<Bidder, BidderDto> {

	/* (non-Javadoc)
	 * @see com.novelerp.core.converter.CustomDozerConverter#convertEntityToDto(com.novelerp.core.entity.PO, java.lang.Class)
	 */
	@Override
	public BidderDto convertEntityToDto(Bidder entity, Class<BidderDto> dtoClass) {
		if(entity==null){
			return null;
		}
		BidderDto bidder= new BidderDto();
		bidder.setBidderId(entity.getBidderId());
		bidder.setBidderStatusCode(entity.getBidderStatusCode());
		return bidder;
	}

}
