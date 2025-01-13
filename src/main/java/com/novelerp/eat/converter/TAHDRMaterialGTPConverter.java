/**
 * @author Ankush
 */
package com.novelerp.eat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.converter.GTPWithTypeConverter;
import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
import com.novelerp.eat.dto.TAHDRMaterialGTPDto;
import com.novelerp.eat.entity.TAHDRMaterialGTP;

@Component
public class TAHDRMaterialGTPConverter extends CustomContextDozerConverter<TAHDRMaterialGTP, TAHDRMaterialGTPDto> implements ObjectConverter<TAHDRMaterialGTP, TAHDRMaterialGTPDto>{

	@Autowired
	private GTPWithTypeConverter gtpWithTypeConverter;
	
	@Override
	public TAHDRMaterialGTPDto convertEntityToDto(TAHDRMaterialGTP entity, Class<TAHDRMaterialGTPDto> dtoClass) {
		if(entity==null){
			return null;
		}
		TAHDRMaterialGTPDto tahdrMaterialGtp= new TAHDRMaterialGTPDto();
		tahdrMaterialGtp.setGtp(gtpWithTypeConverter.convertEntityToDto(entity.getGtp(), GtpParameterDto.class));
		tahdrMaterialGtp.setIsPublish(entity.getIsPublish());
		tahdrMaterialGtp.setTahdrMaterialGtpId(entity.getTahdrMaterialGtpId());
		return tahdrMaterialGtp;
	}

}

