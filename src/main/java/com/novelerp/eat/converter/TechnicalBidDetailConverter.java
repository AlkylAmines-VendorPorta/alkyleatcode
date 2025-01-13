/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.TechnicalBid;
import com.novelerp.eat.service.BidderGtpService;

@Component
public class TechnicalBidDetailConverter extends CustomContextDozerConverter<TechnicalBid, TechnicalBidDto>
		implements ObjectConverter<TechnicalBid, TechnicalBidDto> {

	@Autowired
	private TechnicalBidConverter technicalBidConverter;
	
	@Autowired
	private BidderGtpService bidderGtpService;
	
	@Autowired
	private BidderGtpDetailConverter bidderGtpDetailConverter;
	
	
	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.converter.CustomContextDozerConverter#convertEntityToDto(com.novelerp.appcontext.entity.ContextPO, java.lang.Class)
	 */
	@Override
	public TechnicalBidDto convertEntityToDto(TechnicalBid entity, Class<TechnicalBidDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TechnicalBidDto dto= new TechnicalBidDto();
		dto=technicalBidConverter.convertEntityToDto(entity,TechnicalBidDto.class);
		dto.setBidderGtp(bidderGtpService.getDtoSet(entity.getBidderGtp(), bidderGtpDetailConverter));
		return dto;
	}

}
