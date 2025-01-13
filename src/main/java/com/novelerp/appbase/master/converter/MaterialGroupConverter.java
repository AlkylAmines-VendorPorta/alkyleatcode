package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialGroupDto;
import com.novelerp.appbase.master.entity.MaterialGroup;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

@Component
public class MaterialGroupConverter extends CustomDozerConverter<MaterialGroup, MaterialGroupDto> implements ObjectConverter<MaterialGroup, MaterialGroupDto> {
	
	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public MaterialGroupDto convertEntityToDto(MaterialGroup entity, Class<MaterialGroupDto> dto) {
		if(entity == null){
			return null;
		}
		MaterialGroupDto materialGroup = new MaterialGroupDto();
		materialGroup.setCode(entity.getCode());
		materialGroup.setName(entity.getName());
		materialGroup.setMaterialGroupId(entity.getMaterialGroupId());
		materialGroup.setCreated(entity.getCreated());
		materialGroup.setUpdated(entity.getUpdated());
		materialGroup.setIsActive(entity.getIsActive());
		materialGroup.setDescription(entity.getDescription());
		/*materialGroup.setPartner(getParter(entity));*/
		
		return materialGroup;
	}
	
	private BPartnerDto getParter(MaterialGroup entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}

}
