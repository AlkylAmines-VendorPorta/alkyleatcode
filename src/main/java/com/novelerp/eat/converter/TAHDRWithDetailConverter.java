/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDRDetail;

@Component
public class TAHDRWithDetailConverter extends CustomContextDozerConverter<TAHDRDetail, TAHDRDetailDto>
		implements ObjectConverter<TAHDRDetail, TAHDRDetailDto> {

	@Autowired
	private TAHDRConverter tahdrConverter;
	
	@Autowired
	private DetailConverter tahdrDetailConverter;
		
	@Override
	public TAHDRDetailDto convertEntityToDto(TAHDRDetail entity, Class<TAHDRDetailDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TAHDRDetailDto tahdrDetail=new TAHDRDetailDto();
		tahdrDetail=tahdrDetailConverter.convertEntityToDto(entity, TAHDRDetailDto.class);
		tahdrDetail.setTahdr(tahdrConverter.convertEntityToDto(entity.getTahdr(), TAHDRDto.class));
		return tahdrDetail;
	}
	
}
