package com.novelerp.appbase.master.converter;

import com.novelerp.appbase.master.dto.ChargeDto;
import com.novelerp.appbase.master.entity.Charge;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;

/** 
 * @author Vivek Birdi
 *
 */
public class ChargeConverterPlain extends CustomContextDozerConverter<Charge, ChargeDto> {

	@Override
	public ChargeDto convertEntityToDto(Charge entity, Class<ChargeDto> dtoClass) {
		if(entity == null){
			return null;
		}
		ChargeDto dto =  new ChargeDto();
		dto.setChargeId(entity.getChargeId());
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		return dto;
	}

}
