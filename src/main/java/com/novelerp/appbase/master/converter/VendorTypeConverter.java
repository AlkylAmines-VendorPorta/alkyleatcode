package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.VendorTypeDto;
import com.novelerp.appbase.master.entity.VendorType;
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
public class VendorTypeConverter extends CustomDozerConverter<VendorType, VendorTypeDto> implements ObjectConverter<VendorType, VendorTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public VendorTypeDto convertEntityToDto(VendorType entity, Class<VendorTypeDto> dto) {
		if(entity == null){
			return null;
		}
		VendorTypeDto bidTypeDto = new VendorTypeDto();
		bidTypeDto.setCode(entity.getCode());
		bidTypeDto.setName(entity.getName());
		bidTypeDto.setVendorTypeId(entity.getVendorTypeId());
		bidTypeDto.setCreated(entity.getCreated());
		bidTypeDto.setUpdated(entity.getUpdated());
		bidTypeDto.setIsActive(entity.getIsActive());
		bidTypeDto.setDescription(entity.getDescription());
		/*bidTypeDto.setPartner(getParter(entity));*/
		
		return bidTypeDto;
	}
	
	private BPartnerDto getParter(VendorType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}


