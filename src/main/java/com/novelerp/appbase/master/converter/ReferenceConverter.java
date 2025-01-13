/**
 * @author Ankush
 */
package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.ReferenceDto;
import com.novelerp.appbase.master.entity.Reference;
import com.novelerp.core.converter.CustomDozerConverter;

@Component
public class ReferenceConverter extends CustomDozerConverter<Reference, ReferenceDto>{

	/* (non-Javadoc)
	 * @see com.novelerp.core.converter.CustomDozerConverter#convertEntityToDto(com.novelerp.core.entity.PO, java.lang.Class)
	 */
	@Override
	public ReferenceDto convertEntityToDto(Reference entity, Class<ReferenceDto> dtoClass) {
		if(entity==null){
			return null;
		}
		ReferenceDto referenceDto=new ReferenceDto();
		referenceDto.setReferenceId(entity.getReferenceId());
		referenceDto.setCode(entity.getCode());
		referenceDto.setDescription(entity.getDescription());
		referenceDto.setName(entity.getName());
		return referenceDto;
	}

	
	
}