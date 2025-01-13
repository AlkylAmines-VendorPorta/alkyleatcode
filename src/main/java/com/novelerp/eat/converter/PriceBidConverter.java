/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.PriceBid;

@Component
public class PriceBidConverter extends CustomContextDozerConverter<PriceBid, PriceBidDto>
		implements ObjectConverter<PriceBid, PriceBidDto> {

	
	@Override
	protected PriceBidDto convertEntityToDto(PriceBid entity, Class<PriceBidDto> dtoClass) {/*
		if(entity==null){
			return null;
		}
		PriceBidDto dto=new PriceBidDto();
		dto.setPriceBidId(entity.getPriceBidId());
		dto.setExciseDutyAmount(entity.getExciseDutyAmount());
		dto.setExciseDutyRate(entity.getExciseDutyRate());
		dto.setExGroupPriceRate(entity.getExGroupPriceRate());
		dto.setTotalExGroupPrice(entity.getTotalExGroupPrice());
		dto.setFddAmount(entity.getFddAmount());
		dto.setFddInWords(entity.getFddInWords());
		dto.setFddRate(entity.getFddRate());
		dto.setFreightChargeRate(entity.getFreightChargeRate());
		dto.setTotalFreightCharge(entity.getTotalFreightCharge());
		dto.setIsConfirmed(entity.getIsConfirmed());
		dto.setTaxAmount(entity.getTaxAmount());
		dto.setTaxRate(entity.getTaxRate());
		dto.setTicRate(entity.getTicRate());
		dto.setTotalTic(entity.getTotalTic());*/
		return null;
	}

}
