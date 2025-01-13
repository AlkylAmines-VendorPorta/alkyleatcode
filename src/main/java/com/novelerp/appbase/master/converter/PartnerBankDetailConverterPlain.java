package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.PartnerBankDetailDto;
import com.novelerp.appbase.master.entity.PartnerBankDetail;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Component
public class PartnerBankDetailConverterPlain extends CustomContextDozerConverter<PartnerBankDetail, PartnerBankDetailDto>{

	@Override
	public PartnerBankDetailDto convertEntityToDto(PartnerBankDetail entity, Class<PartnerBankDetailDto> dtoClass) {
	
		if(entity== null){
			return null;
		}
		
		PartnerBankDetailDto dto =  new PartnerBankDetailDto();
		dto.setPartnerBankDetailId(entity.getPartnerBankDetailId());
		/*dto.setBankName(entity.getBankName());*/
		dto.setAccountNumber(entity.getAccountNumber());
		dto.setBenificaryName(entity.getBenificaryName());
		/*dto.setIfscCode(entity.getIfscCode());*/
		dto.setMobileNo(entity.getMobileNo());
		return dto;
	}
	

}
