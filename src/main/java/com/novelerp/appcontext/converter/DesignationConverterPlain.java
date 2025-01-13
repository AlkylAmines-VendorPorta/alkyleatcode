package com.novelerp.appcontext.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.entity.Designation;
import com.novelerp.core.converter.ObjectConverter;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Component
public class DesignationConverterPlain extends CustomContextDozerConverter<Designation, DesignationDto> implements ObjectConverter<Designation, DesignationDto>{

	@Override
	public DesignationDto convertEntityToDto(Designation entity, Class<DesignationDto> dtoClass) {

		DesignationDto designationDto = new DesignationDto();
		designationDto.setCode(entity.getCode());
		designationDto.setName(entity.getName());
		designationDto.setDesignationId(entity.getDesignationId());
		designationDto.setDescription(entity.getDescription());
		return designationDto;
	}

}
