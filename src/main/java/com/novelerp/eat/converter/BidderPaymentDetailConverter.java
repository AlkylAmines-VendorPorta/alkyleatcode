/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.entity.Bidder;

@Component
public class BidderPaymentDetailConverter extends CustomContextDozerConverter<Bidder, BidderDto>
		implements ObjectConverter<Bidder, BidderDto> {

	@Autowired
	private BidderConverter bidderConverter;

	@Autowired
	private PaymentDetailConverter paymentDetailConverter;
	
	@Autowired
	private TAHDRWithDetailConverter tahdrDetailConverter;
	
	@Override
	public BidderDto convertEntityToDto(Bidder entity, Class<BidderDto> dtoClass) {
		if(entity==null){
			return null;
		}
		BidderDto dto= new BidderDto();
		dto=bidderConverter.convertEntityToDto(entity, BidderDto.class);
		dto.setTenderPurchase(paymentDetailConverter.convertEntityToDto(entity.getTenderPurchase(), PaymentDetailDto.class));
		dto.setTenderDetail(tahdrDetailConverter.convertEntityToDto(entity.getTenderDetail(), TAHDRDetailDto.class));
		dto.setTahdr(dto.getTenderDetail().getTahdr());
		return dto;
	}

}
