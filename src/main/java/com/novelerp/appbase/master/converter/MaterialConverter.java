package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.entity.Material;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class MaterialConverter extends CustomDozerConverter<Material, MaterialDto> implements ObjectConverter<Material, MaterialDto>{
	
	
	@Override
	public MaterialDto convertEntityToDto(Material entity, Class<MaterialDto> dto) {
		if(entity == null){
			return null;
		}
		MaterialDto materialDto = new MaterialDto();
		materialDto.setCode(entity.getCode());
		materialDto.setName(entity.getName());
		materialDto.setMaterialId(entity.getMaterialId());
		materialDto.setIsPropReport(entity.getIsPropReport());
		materialDto.setIsTestReport(entity.getIsTestReport());
		materialDto.setIsSSI(entity.getIsSSI());
		materialDto.setIsNSIC(entity.getIsNSIC());
		materialDto.setDescription(entity.getDescription());
		materialDto.setItemCode(entity.getItemCode());
		/*materialDto.setHsnCode(entity.getHsnCode());*/
		materialDto.setItemTrade(entity.getItemTrade());
		return materialDto;
	}
	
	/*private BPartnerDto getParter(Material entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}*/



}

