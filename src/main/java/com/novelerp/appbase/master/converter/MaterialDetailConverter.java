/**
 * @author Ankush
 */
package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.HSNDto;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialGroupDto;
import com.novelerp.appbase.master.dto.MaterialSubGroupDto;
import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.Material;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class MaterialDetailConverter extends CustomDozerConverter<Material, MaterialDto>
		implements ObjectConverter<Material, MaterialDto> {

	@Autowired
	private MaterialConverter materialConverter;
	
	@Autowired
	private UomConverter uomConverter;
	
	@Autowired
	private HSNConverter hsnConverter;
	
	@Autowired
	private MaterialGroupConverter materialGroupConverter;
	
	@Autowired
	private MaterialSubGroupConverter materialSubGroupConverter;
	
	@Override
	public MaterialDto convertEntityToDto(Material entity, Class<MaterialDto> dtoClass) {
		
		MaterialDto material= new MaterialDto();
		material=materialConverter.convertEntityToDto(entity, MaterialDto.class);
		material.setUom(uomConverter.convertEntityToDto(entity.getUom(), UOMDto.class));
		material.setHsnCode(hsnConverter.convertEntityToDto(entity.getHsnCode(), HSNDto.class));
		material.setMaterialGroup(materialGroupConverter.convertEntityToDto(entity.getMaterialGroup(), MaterialGroupDto.class));
		material.setMaterialSubGroup(materialSubGroupConverter.convertEntityToDto(entity.getMaterialSubGroup(), MaterialSubGroupDto.class));
		return material;
	}

}
