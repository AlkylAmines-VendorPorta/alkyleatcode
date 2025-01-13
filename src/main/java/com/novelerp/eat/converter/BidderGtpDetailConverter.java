/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;
import com.novelerp.eat.entity.BidderGtp;

@Component
public class BidderGtpDetailConverter extends CustomContextDozerConverter<BidderGtp, BidderGtpDto>
		implements ObjectConverter<BidderGtp, BidderGtpDto> {

	@Autowired
	private BidderGtpConverter bidderGtpConverter;
	
	@Autowired
	private TAHDRMaterialGTPConverter tahdrMaterialGtpConverter;
	
	@Override
	public BidderGtpDto convertEntityToDto(BidderGtp entity, Class<BidderGtpDto> dtoClass) {
		if(entity==null){
			return null;
		}
		BidderGtpDto dto=new BidderGtpDto();
		dto=bidderGtpConverter.convertEntityToDto(entity, BidderGtpDto.class);
		dto.setTahdrMaterialgtp(tahdrMaterialGtpConverter.convertEntityToDto(entity.getTahdrMaterialgtp(), TAHDRMaterialGTPDto.class));
		return dto;
	}
	
}
