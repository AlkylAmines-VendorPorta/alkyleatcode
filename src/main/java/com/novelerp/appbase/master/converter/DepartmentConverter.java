package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.entity.Department;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class DepartmentConverter extends CustomDozerConverter<Department, DepartmentDto> implements ObjectConverter<Department, DepartmentDto>{

	@Override
	public DepartmentDto convertEntityToDto(Department entity, Class<DepartmentDto> dto) {
		if(entity == null){
			return null;
		}
		DepartmentDto DepartmentDto = new DepartmentDto();
		DepartmentDto.setCode(entity.getCode());
		DepartmentDto.setName(entity.getName());
		DepartmentDto.setDepartmentId(entity.getDepartmentId());
		DepartmentDto.setCreated(entity.getCreated());
		DepartmentDto.setUpdated(entity.getUpdated());
		DepartmentDto.setIsActive(entity.getIsActive());
		DepartmentDto.setDescription(entity.getDescription());
		//DepartmentDto.setPartner(getParter(entity));
		
		return DepartmentDto;
	}
	
}

