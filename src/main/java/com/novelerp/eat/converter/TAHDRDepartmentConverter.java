/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.converter.DepartmentConverter;
import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDR;

@Component
public class TAHDRDepartmentConverter extends CustomContextDozerConverter<TAHDR, TAHDRDto> implements ObjectConverter<TAHDR, TAHDRDto>{

	@Autowired
	private TAHDRConverter tahdrConverter;
	
	@Autowired
	private DepartmentConverter departmentConverter;
	
	@Override
	public TAHDRDto convertEntityToDto(TAHDR entity, Class<TAHDRDto> dtoClass){
		TAHDRDto dto=tahdrConverter.convertEntityToDto(entity, TAHDRDto.class);
		dto.setDepartment(departmentConverter.convertEntityToDto(entity.getDepartment(), DepartmentDto.class));
		return dto;
	}
	
}
