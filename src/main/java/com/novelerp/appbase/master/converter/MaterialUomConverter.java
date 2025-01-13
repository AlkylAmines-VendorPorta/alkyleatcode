/**
 * @author Ankush
 */
package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.Material;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class MaterialUomConverter extends CustomDozerConverter<Material, MaterialDto>
		implements ObjectConverter<Material, MaterialDto> {

	@Autowired
	private MaterialConverter materialConverter;
	
	@Autowired
	private UomConverter uomConverter;
	
	@Override
	public MaterialDto convertEntityToDto(Material entity, Class<MaterialDto> dtoClass) {
		if(entity==null){
			return null;
		}
		MaterialDto material= new MaterialDto();
		material=materialConverter.convertEntityToDto(entity, MaterialDto.class);
		material.setUom(uomConverter.convertEntityToDto(entity.getUom(), UOMDto.class));
		return material;
	}

}
