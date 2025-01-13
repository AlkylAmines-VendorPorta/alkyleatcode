/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.entity.TAHDRDetail;
import com.novelerp.eat.service.TAHDRMaterialService;

@Component
public class TahdrDetailMaterialUomGtpConverter extends CustomContextDozerConverter<TAHDRDetail, TAHDRDetailDto>
		implements ObjectConverter<TAHDRDetail, TAHDRDetailDto> {

	@Autowired
	private TAHDRWithDetailConverter tahdrWithDetailConverter;
	
	@Autowired
	private TAHDRMaterialService tmService;
	
	@Autowired
	private TAHDRMaterialUomWithGtpConverter tmugConverter;
	
	
	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.converter.CustomContextDozerConverter#convertEntityToDto(com.novelerp.appcontext.entity.ContextPO, java.lang.Class)
	 */
	@Override
	public TAHDRDetailDto convertEntityToDto(TAHDRDetail entity, Class<TAHDRDetailDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TAHDRDetailDto dto=new TAHDRDetailDto();
		dto=tahdrWithDetailConverter.convertEntityToDto(entity, TAHDRDetailDto.class);
		/*dto.setTahdrMaterial(tmService.getDtoSet(entity.getTahdrMaterial(), tmugConverter));*/
		return dto;
	}

}
