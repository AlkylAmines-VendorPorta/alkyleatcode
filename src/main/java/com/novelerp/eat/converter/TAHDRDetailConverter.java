/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDR;
import com.novelerp.eat.service.TAHDRDetailService;

@Component
public class TAHDRDetailConverter extends CustomContextDozerConverter<TAHDR, TAHDRDto>
		implements ObjectConverter<TAHDR, TAHDRDto> {
		
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	
	@Autowired
	private TAHDRDepartmentConverter tahdrDeptConverter;
	
	@Override
	public TAHDRDto convertEntityToDto(TAHDR entity, Class<TAHDRDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TAHDRDto tahdr=new TAHDRDto();
		tahdr=tahdrDeptConverter.convertEntityToDto(entity, TAHDRDto.class);
		tahdr.setTahdrDetail(tahdrDetailService.getDtoSet(entity.getTahdrDetail(),null));
		return tahdr;
	}

}
