package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.UOM;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class UomConverter extends CustomDozerConverter<UOM, UOMDto> implements ObjectConverter<UOM, UOMDto>{
	
	@Autowired
	private PartnerConverter partnerConverter;
	
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
		/*uom.setPartner(getParter(entity));*/
		return uom;
	}
	
	private BPartnerDto getParter(UOM entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}

}
