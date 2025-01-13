package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerSignatoryDto;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.appcontext.entity.UserDetails;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author {Aman / Vivek Birdi}
 *
 */
@Component
public class PartnerSignatoryConverterPlain extends CustomContextDozerConverter<PartnerSignatory, PartnerSignatoryDto> implements ObjectConverter<PartnerSignatory, PartnerSignatoryDto>{
	
	@Override
	public PartnerSignatoryDto convertEntityToDto(PartnerSignatory entity,
			Class<PartnerSignatoryDto> dtoClass) {
		if(entity==null){
			return null;
		}
		PartnerSignatoryDto dto =  new PartnerSignatoryDto();
		dto.setPartnerSignatoryId(entity.getPartnerSignatoryId());
		UserDetails userDetail = entity.getUserDetail();
		userDetail.setLocation(null);
		return dto;
	}
	
	

}
