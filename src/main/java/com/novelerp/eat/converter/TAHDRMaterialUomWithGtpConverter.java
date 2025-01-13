/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.TAHDRMaterial;
import com.novelerp.eat.service.TAHDRMaterialGTPService;

@Component
public class TAHDRMaterialUomWithGtpConverter extends CustomContextDozerConverter<TAHDRMaterial, TAHDRMaterialDto>
		implements ObjectConverter<TAHDRMaterial, TAHDRMaterialDto> {

	@Autowired
	private TahdrMaterialWithUomConverter tahdrMaterialUomConverter;
	
	@Autowired
	private TAHDRMaterialGTPService tmgService;
	
	@Autowired
	private TAHDRMaterialGTPConverter tmgConverter;
	
	/* (non-Javadoc)
	 * @see com.novelerp.appcontext.converter.CustomContextDozerConverter#convertEntityToDto(com.novelerp.appcontext.entity.ContextPO, java.lang.Class)
	 */
	@Override
	public TAHDRMaterialDto convertEntityToDto(TAHDRMaterial entity, Class<TAHDRMaterialDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TAHDRMaterialDto dto= new TAHDRMaterialDto();
		dto=tahdrMaterialUomConverter.convertEntityToDto(entity, TAHDRMaterialDto.class);
		dto.setMaterialGtpList(tmgService.getDtoSet(entity.getMaterialGtpList(), tmgConverter));
		return dto;
	}

}
