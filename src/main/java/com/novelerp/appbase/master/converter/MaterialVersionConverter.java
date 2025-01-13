package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialVersionDto;
import com.novelerp.appbase.master.entity.MaterialVersion;
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
public class MaterialVersionConverter extends CustomDozerConverter<MaterialVersion, MaterialVersionDto> implements ObjectConverter<MaterialVersion, MaterialVersionDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public MaterialVersionDto convertEntityToDto(MaterialVersion entity, Class<MaterialVersionDto> dto) {
		if(entity == null){
			return null;
		}
		MaterialVersionDto materialVersionDto = new MaterialVersionDto();
		materialVersionDto.setCode(entity.getCode());
		materialVersionDto.setName(entity.getName());
		materialVersionDto.setMaterialVersionId(entity.getMaterialVersionId());
		/*MaterialVersionDto.setCreated(entity.getCreated());
		MaterialVersionDto.setUpdated(entity.getUpdated());*/
		materialVersionDto.setIsActive(entity.getIsActive());
		materialVersionDto.setDescription(entity.getDescription());
		materialVersionDto.setPartner(getParter(entity));
		if(entity.getMaterial()!=null)
		{
			MaterialDto materialDto=new MaterialDto();
			materialDto.setMaterialId(entity.getMaterial().getMaterialId());
			materialDto.setName(entity.getMaterial().getName());
			materialVersionDto.setMaterial(materialDto);
		}
		
		return materialVersionDto;
	}
	
	private BPartnerDto getParter(MaterialVersion entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}
