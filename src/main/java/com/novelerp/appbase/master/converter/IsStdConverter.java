package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.IsStdDto;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.entity.IsStd;
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
public class IsStdConverter extends CustomDozerConverter<IsStd, IsStdDto> implements ObjectConverter<IsStd, IsStdDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public IsStdDto convertEntityToDto(IsStd entity, Class<IsStdDto> dto) {
		if(entity == null){
			return null;
		}
		IsStdDto IsStdDto = new IsStdDto();
		IsStdDto.setCode(entity.getCode());
		IsStdDto.setName(entity.getName());
		IsStdDto.setIsStdId(entity.getIsStdId());
		IsStdDto.setCreated(entity.getCreated());
		IsStdDto.setUpdated(entity.getUpdated());
		IsStdDto.setIsActive(entity.getIsActive());
		IsStdDto.setDescription(entity.getDescription());
	/*	IsStdDto.setPartner(getParter(entity));*/
		
		if(entity.getMaterial()!=null)
		{
			MaterialDto material=new MaterialDto();
			material.setMaterialId(entity.getMaterial().getMaterialId());
			/*material.setName(entity.getMaterial().getName());*/
			IsStdDto.setMaterial(material);
		}
		
		return IsStdDto;
	}
	
	private BPartnerDto getParter(IsStd entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}
