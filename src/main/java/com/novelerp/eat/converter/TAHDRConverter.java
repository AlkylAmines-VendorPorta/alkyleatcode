package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDR;

/**
 * @author Ankita Tirodkar
 *
 */
@Component
public class TAHDRConverter extends CustomContextDozerConverter<TAHDR, TAHDRDto> implements ObjectConverter<TAHDR, TAHDRDto> {

	@Override
	public TAHDRDto convertEntityToDto(TAHDR entity, Class<TAHDRDto> dtoClass) {
		if(entity == null){
			return null;
		}
		TAHDRDto tahdrDto = new TAHDRDto();
		tahdrDto.setBidTypeCode(entity.getBidTypeCode());
		tahdrDto.setTahdrId(entity.getTahdrId());
		tahdrDto.setTahdrCode(entity.getTahdrCode());
		tahdrDto.setTitle(entity.getTitle());
		tahdrDto.setTahdrTypeCode(entity.getTahdrTypeCode());
		tahdrDto.setBudgetType(entity.getBudgetType());
		/*tahdrDto.setOfficeType(entity.getOfficeType());*/
		tahdrDto.setSchemeName(entity.getSchemeName());
		tahdrDto.setSchemeCode(entity.getSchemeCode());
		tahdrDto.setTahdrStatusCode(entity.getTahdrStatusCode());
		return tahdrDto;
	}
}
