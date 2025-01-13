/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.converter.MaterialUomConverter;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.TAHDRMaterial;

@Component
public class TahdrMaterialWithUomConverter extends CustomContextDozerConverter<TAHDRMaterial, TAHDRMaterialDto>
		implements ObjectConverter<TAHDRMaterial, TAHDRMaterialDto> {

	@Autowired
	private TAHDRMaterialConverter tahdrMaterialConverter;
	
	@Autowired
	private MaterialUomConverter materialUomConverter;
	
	@Override
	public TAHDRMaterialDto convertEntityToDto(TAHDRMaterial entity, Class<TAHDRMaterialDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TAHDRMaterialDto dto = new TAHDRMaterialDto();
		dto=tahdrMaterialConverter.convertEntityToDto(entity, TAHDRMaterialDto.class);
		dto.setMaterial(materialUomConverter.convertEntityToDto(entity.getMaterial(), MaterialDto.class));
		return dto;
	}

}
