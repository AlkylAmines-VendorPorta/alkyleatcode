package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.entity.BidderGtp;

/**
 * @author Aman Sahu
 *
 */
@Component
public class BidderGtpConverter extends CustomContextDozerConverter<BidderGtp, BidderGtpDto> implements ObjectConverter<BidderGtp, BidderGtpDto> {

@Override
public BidderGtpDto convertEntityToDto(BidderGtp entity, Class<BidderGtpDto> dtoClass) {
		if(entity==null){
			return null;
		}
		BidderGtpDto dto=new BidderGtpDto();
		dto.setBidderGtpId(entity.getBidderGtpId());
		return dto;
		}
}
