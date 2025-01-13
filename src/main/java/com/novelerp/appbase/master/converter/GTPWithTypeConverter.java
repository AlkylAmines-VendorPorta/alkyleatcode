/**
 * @author Ankush
 */
package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.dto.GtpParameterTypeDto;
import com.novelerp.appbase.master.entity.GtpParameter;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class GTPWithTypeConverter extends CustomContextDozerConverter<GtpParameter, GtpParameterDto>
		implements ObjectConverter<GtpParameter, GtpParameterDto> {

	@Autowired
	private GtpParameterConverter gtpConverter;
	
	@Autowired
	private GtpParameterTypeConverter gtpTypeConverter;
	
	@Override
	public GtpParameterDto convertEntityToDto(GtpParameter entity, Class<GtpParameterDto> dtoClass) {
		if(entity==null){
			return null;
		}
		GtpParameterDto gtp= new GtpParameterDto();
		gtp=gtpConverter.convertEntityToDto(entity, GtpParameterDto.class);
		gtp.setGtpParameterType(gtpTypeConverter.convertEntityToDto(entity.getGtpParameterType(), GtpParameterTypeDto.class));
		return gtp;
	}

}
