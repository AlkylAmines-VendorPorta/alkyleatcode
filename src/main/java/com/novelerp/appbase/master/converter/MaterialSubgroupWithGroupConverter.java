/**
 * @author Ankush
 */
package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialGroupDto;
import com.novelerp.appbase.master.dto.MaterialSubGroupDto;
import com.novelerp.appbase.master.entity.MaterialSubGroup;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class MaterialSubgroupWithGroupConverter extends CustomDozerConverter<MaterialSubGroup, MaterialSubGroupDto>
		implements ObjectConverter<MaterialSubGroup, MaterialSubGroupDto> {

	@Autowired
	private MaterialSubGroupConverter subGrpConverter;
	
	@Autowired
	private MaterialGroupConverter grpConverter;
	
	@Override
	public MaterialSubGroupDto convertEntityToDto(MaterialSubGroup entity, Class<MaterialSubGroupDto> dtoClass) {
		if(entity == null){
			return null;
		}
		MaterialSubGroupDto subGroup= new MaterialSubGroupDto();
		subGroup=subGrpConverter.convertEntityToDto(entity, MaterialSubGroupDto.class);
		subGroup.setMaterialGroup(grpConverter.convertEntityToDto(entity.getMaterialGroup(), MaterialGroupDto.class));
		return subGroup;
	}

}
