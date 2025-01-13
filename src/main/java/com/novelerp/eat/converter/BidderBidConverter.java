/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.entity.Bidder;
import com.novelerp.eat.service.ItemBidService;

@Component
public class BidderBidConverter extends CustomContextDozerConverter<Bidder, BidderDto>
		implements ObjectConverter<Bidder, BidderDto> {

	@Autowired
	private BidderConverter bidderConverter;

	@Autowired
	private ItemBidDetailConverter itemBidDetailConverter;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Override
	public BidderDto convertEntityToDto(Bidder entity, Class<BidderDto> dtoClass) {
		if(entity==null){
			return null;
		}
		BidderDto bidderDto= new BidderDto();
		bidderDto=bidderConverter.convertEntityToDto(entity, BidderDto.class);
		bidderDto.setItemBidList(itemBidService.getDtoSet(entity.getItemBidList(),itemBidDetailConverter));
		return bidderDto;
	}

}
