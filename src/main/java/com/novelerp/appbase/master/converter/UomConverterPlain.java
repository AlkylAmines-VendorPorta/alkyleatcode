package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.UOM;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class UomConverterPlain extends CustomDozerConverter<UOM, UOMDto> implements ObjectConverter<UOM, UOMDto>{
		
	@Override
	public UOMDto convertEntityToDto(UOM entity, Class<UOMDto> dto) {
		if(entity == null){
			return null;
		}
		UOMDto uom =  new UOMDto();
		uom.setCode(entity.getCode());
		uom.setName(entity.getName());
		uom.setUomId(entity.getUomId());
		uom.setCreated(entity.getCreated());
		uom.setUpdated(uom.getUpdated());
		uom.setIsActive(entity.getIsActive());
		uom.setDescription(entity.getDescription());
		return uom;
	}
	
}
