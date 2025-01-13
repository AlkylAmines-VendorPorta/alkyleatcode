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
import com.novelerp.eat.service.TAHDRMaterialService;

@Component
public class TahdrDetailWithMaterialUomConverter extends CustomContextDozerConverter<TAHDRDetail, TAHDRDetailDto>
		implements ObjectConverter<TAHDRDetail, TAHDRDetailDto> {

	@Autowired
	private TAHDRConverter tahdrConverter;
	
	@Autowired
	private DetailConverter detailConverter;
	
	@Autowired
	private TahdrMaterialWithUomConverter tahdrMaterialUomConverter;
	
	@Autowired
	private TAHDRMaterialService tahdrMaterialService;
	
	@Override
	public TAHDRDetailDto convertEntityToDto(TAHDRDetail entity, Class<TAHDRDetailDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TAHDRDetailDto dto= new TAHDRDetailDto();
		dto=detailConverter.convertEntityToDto(entity, TAHDRDetailDto.class);
		dto.setTahdr(tahdrConverter.convertEntityToDto(entity.getTahdr(), TAHDRDto.class));
		/*dto.setTahdrMaterial(tahdrMaterialService.getDtoSet(entity.getTahdrMaterial(), tahdrMaterialUomConverter));*/
		return dto;
	}

}
