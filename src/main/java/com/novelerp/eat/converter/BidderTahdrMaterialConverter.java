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
import com.novelerp.eat.service.TAHDRDetailService;

@Component
public class BidderTahdrMaterialConverter extends CustomContextDozerConverter<Bidder, BidderDto>
		implements ObjectConverter<Bidder, BidderDto> {

	@Autowired
	private BidderConverter bidderConverter;
	
	@Autowired
	private TahdrDetailWithMaterialUomConverter tahdrDetailWithMaterialUomConverter;
	
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Override
	public BidderDto convertEntityToDto(Bidder entity, Class<BidderDto> dtoClass) {
		if(entity==null){
			return null;
		}
		BidderDto dto=bidderConverter.convertEntityToDto(entity, BidderDto.class);
		dto.setTenderDetail(tahdrDetailService.getDto(entity.getTenderDetail(), tahdrDetailWithMaterialUomConverter));
		dto.setTahdr(dto.getTenderDetail().getTahdr());
		return dto;
	}

}
