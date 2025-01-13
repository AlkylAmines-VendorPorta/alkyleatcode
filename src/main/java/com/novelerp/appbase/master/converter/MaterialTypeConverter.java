package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialTypeDto;
import com.novelerp.appbase.master.entity.MaterialType;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class MaterialTypeConverter extends CustomDozerConverter<MaterialType, MaterialTypeDto> implements ObjectConverter<MaterialType, MaterialTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public MaterialTypeDto convertEntityToDto(MaterialType entity, Class<MaterialTypeDto> dto) {
		if(entity == null){
			return null;
		}
		MaterialTypeDto materialTypeDto = new MaterialTypeDto();
		materialTypeDto.setCode(entity.getCode());
		materialTypeDto.setName(entity.getName());
		materialTypeDto.setMaterialTypeId(entity.getMaterialTypeId());
		materialTypeDto.setCreated(entity.getCreated());
		materialTypeDto.setUpdated(entity.getUpdated());
		materialTypeDto.setIsActive(entity.getIsActive());
		materialTypeDto.setDescription(entity.getDescription());
		materialTypeDto.setPartner(getParter(entity));
		
		return materialTypeDto;
	}
	
	private BPartnerDto getParter(MaterialType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}


}
