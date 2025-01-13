package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.GtpParameterTypeDto;
import com.novelerp.appbase.master.entity.GtpParameterType;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class GtpParameterTypeConverter extends CustomContextDozerConverter<GtpParameterType, GtpParameterTypeDto> implements ObjectConverter<GtpParameterType, GtpParameterTypeDto>{
	
	@Override
	public GtpParameterTypeDto convertEntityToDto(GtpParameterType entity, Class<GtpParameterTypeDto> dto) {
		if(entity == null){
			return null;
		}
		GtpParameterTypeDto GtpParameterTypeDto = new GtpParameterTypeDto();
		GtpParameterTypeDto.setCode(entity.getCode());
		GtpParameterTypeDto.setName(entity.getName());
		GtpParameterTypeDto.setGtpParameterTypeId(entity.getGtpParameterTypeId());
		GtpParameterTypeDto.setCreated(entity.getCreated());
		GtpParameterTypeDto.setUpdated(entity.getUpdated());
		GtpParameterTypeDto.setIsActive(entity.getIsActive());
		GtpParameterTypeDto.setDescription(entity.getDescription());
		return GtpParameterTypeDto;
	}
}


