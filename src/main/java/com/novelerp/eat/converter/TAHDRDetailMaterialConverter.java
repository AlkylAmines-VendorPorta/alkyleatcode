/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.converter.MaterialUomConverter;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRMaterialDto;
import com.novelerp.eat.entity.TAHDRMaterial;

@Component
public class TAHDRDetailMaterialConverter extends CustomDozerConverter<TAHDRMaterial, TAHDRMaterialDto>
		implements ObjectConverter<TAHDRMaterial, TAHDRMaterialDto> {

	@Autowired
	private DetailConverter detailConverter;
	
	@Autowired
	private TAHDRMaterialConverter tahdrMaterialConverter;
	
	@Autowired
	private MaterialUomConverter materialConverter;
	
	@Override
	public TAHDRMaterialDto convertEntityToDto(TAHDRMaterial entity, Class<TAHDRMaterialDto> dtoClass) {
		TAHDRMaterialDto tahdrMaterial= new TAHDRMaterialDto();
		tahdrMaterial=tahdrMaterialConverter.convertEntityToDto(entity, TAHDRMaterialDto.class);
		tahdrMaterial.setMaterial(materialConverter.convertEntityToDto(entity.getMaterial(), MaterialDto.class));
		tahdrMaterial.setTahdrDetail(detailConverter.convertEntityToDto(entity.getTahdrDetail(), TAHDRDetailDto.class));
		return tahdrMaterial;
	}

}
