package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.dto.GtpParameterTypeDto;
import com.novelerp.appbase.master.dto.HSNDto;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.entity.GtpParameter;
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
public class GtpParameterConverter extends CustomDozerConverter<GtpParameter, GtpParameterDto> implements ObjectConverter<GtpParameter, GtpParameterDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Autowired 
	private HSNConverter hsnConverter;
	@Override
	public GtpParameterDto convertEntityToDto(GtpParameter entity, Class<GtpParameterDto> dto) {
		if(entity == null){
			return null;
		}
		GtpParameterDto gtpParameterDto = new GtpParameterDto();
		gtpParameterDto.setCode(entity.getCode());
		gtpParameterDto.setName(entity.getName());
		gtpParameterDto.setGtpParameterId(entity.getGtpParameterId());
		gtpParameterDto.setIsCopied(entity.getIsCopied());
		/*GtpParameterDto.setCreated(entity.getCreated());
		GtpParameterDto.setUpdated(entity.getUpdated());*/
		gtpParameterDto.setIsActive(entity.getIsActive());
		gtpParameterDto.setDescription(entity.getDescription());
		/*gtpParameterDto.setPartner(getParter(entity));*/
		if(entity.getGtpParameterType()!=null)
		{
			GtpParameterTypeDto gtpParametertype=new GtpParameterTypeDto();
			gtpParametertype.setGtpParameterTypeId(entity.getGtpParameterType().getGtpParameterTypeId());
			gtpParametertype.setName(entity.getGtpParameterType().getName());
			gtpParameterDto.setGtpParameterType(gtpParametertype);
		}
		if(entity.getMaterial()!=null)
		{
			MaterialDto material=new MaterialDto();
			material.setMaterialId(entity.getMaterial().getMaterialId());
		    material.setName(entity.getMaterial().getName());
		    material.setItemCode(entity.getMaterial().getItemCode());
		    material.setItemTrade(entity.getMaterial().getItemTrade());
		    material.setHsnCode(hsnConverter.convertEntityToDto(entity.getMaterial().getHsnCode(), HSNDto.class));
		   /* if(entity.getMaterial().getHsnCode()!=null){
		    	HSNDto hsn=new HSNDto();
		    	hsn.setHsnId(entity.getMaterial().getHsnCode().getHsnId());
		    	hsn.setName(entity.getMaterial().getHsnCode().getName());
		    	hsn.setCode(entity.getMaterial().getHsnCode().getCode());
		    	material.setHsnCode(hsn);
		    }*/
		    
		    gtpParameterDto.setMaterial(material);
			
		}
		
		
		return gtpParameterDto;
	}
	
	private BPartnerDto getParter(GtpParameter entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}

