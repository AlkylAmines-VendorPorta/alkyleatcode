package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.HSNDto;
import com.novelerp.appbase.master.entity.HSN;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class HSNConverter extends CustomDozerConverter<HSN, HSNDto> implements ObjectConverter<HSN, HSNDto>{

	@Override
	public HSNDto convertEntityToDto(HSN entity, Class<HSNDto> dto) {
		if(entity == null){
			return null;
		}
		HSNDto hsnDto = new HSNDto();
		hsnDto.setCode(entity.getCode());
		hsnDto.setName(entity.getName());
		hsnDto.setDescription(entity.getDescription());
		hsnDto.setHsnId(entity.getHsnId());
		
		return hsnDto;
	}
}
