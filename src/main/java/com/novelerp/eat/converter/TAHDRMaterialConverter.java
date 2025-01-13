/**
 * @author Ankush
 */

package com.novelerp.eat.converter;

import org.springframework.stereotype.Component;

import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.TAHDRMaterial;

@Component
public class TAHDRMaterialConverter extends CustomDozerConverter<TAHDRMaterial, TAHDRMaterialDto>
		implements ObjectConverter<TAHDRMaterial, TAHDRMaterialDto> {
	
	@Override
	public TAHDRMaterialDto convertEntityToDto(TAHDRMaterial entity, Class<TAHDRMaterialDto> dtoClass) {
		if(entity == null){
			return null;
		}
		TAHDRMaterialDto dto=new TAHDRMaterialDto();
		dto.setTahdrMaterialId(entity.getTahdrMaterialId());
		dto.setAboutSpecCode(entity.getAboutSpecCode());
		dto.setMaterialDescription(entity.getMaterialDescription());
		dto.setQuantity(entity.getQuantity());
		dto.setSpecVersion(entity.getSpecVersion());
		dto.setMaterialTypeCode(entity.getMaterialTypeCode());
		dto.setBasePriceRate(entity.getBasePriceRate());
		return dto;
	}

}
