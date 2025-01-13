package com.novelerp.appbase.master.converter;

import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.BomVersionDto;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.MaterialSpecificationDto;
import com.novelerp.appbase.master.entity.MaterialSpecification;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class MaterialSpecificationConverter extends CustomContextDozerConverter<MaterialSpecification, MaterialSpecificationDto> implements ObjectConverter<MaterialSpecification, MaterialSpecificationDto>{

	/*@Autowired
	private PartnerConverter partnerConverter;*/
	
	@Override
	public MaterialSpecificationDto convertEntityToDto(MaterialSpecification entity, Class<MaterialSpecificationDto> dto) {
		if(entity == null){
			return null;
		}
		MaterialSpecificationDto materialSpecificationDto = new MaterialSpecificationDto();
		materialSpecificationDto.setCode(entity.getCode());
		materialSpecificationDto.setName(entity.getName());
		materialSpecificationDto.setMaterialSpecificationId(entity.getMaterialSpecificationId());
		materialSpecificationDto.setQuantity(entity.getQuantity());
		materialSpecificationDto.setIsActive(entity.getIsActive());
		materialSpecificationDto.setDescription(entity.getDescription());
		if(entity.getMaterial()!=null){
			BomVersionDto bomVersion=new BomVersionDto();
			bomVersion.setBomVersionId(entity.getMaterial().getBomVersionId());
			bomVersion.setName(entity.getMaterial().getName());
			 materialSpecificationDto.setMaterial(bomVersion);
		}
		
		if(entity.getSpecification()!=null)
		{
		   MaterialDto material=new MaterialDto();
		   material.setMaterialId(entity.getSpecification().getMaterialId());
		   material.setName(entity.getSpecification().getName());
		   materialSpecificationDto.setSpecification(material);
		}
		return materialSpecificationDto;
	}
	
/*	private BPartnerDto getParter(MaterialSpecification entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}*/



}
