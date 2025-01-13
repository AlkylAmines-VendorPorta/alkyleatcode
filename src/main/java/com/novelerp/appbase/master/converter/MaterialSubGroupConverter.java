package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialSubGroupDto;
import com.novelerp.appbase.master.entity.MaterialSubGroup;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class MaterialSubGroupConverter extends CustomDozerConverter<MaterialSubGroup, MaterialSubGroupDto> implements ObjectConverter<MaterialSubGroup, MaterialSubGroupDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public MaterialSubGroupDto convertEntityToDto(MaterialSubGroup entity, Class<MaterialSubGroupDto> dto) {
		if(entity == null){
			return null;
		}
		MaterialSubGroupDto materialSubGroupDto = new MaterialSubGroupDto();
		materialSubGroupDto.setCode(entity.getCode());
		materialSubGroupDto.setName(entity.getName());
		materialSubGroupDto.setMaterialSubGroupId(entity.getMaterialSubGroupId());
		materialSubGroupDto.setIsActive(entity.getIsActive());
		materialSubGroupDto.setDescription(entity.getDescription());
		return materialSubGroupDto;
	}
	
	private BPartnerDto getParter(MaterialSubGroup entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}
