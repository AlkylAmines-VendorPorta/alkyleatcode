package com.novelerp.appcontext.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.BPartnerGroupDto;
import com.novelerp.appcontext.entity.BPartnerGroup;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * @author Aman Sahu
 *
 */
@Component
public class BPartnerGroupConverter extends CustomDozerConverter<BPartnerGroup, BPartnerGroupDto> implements ObjectConverter<BPartnerGroup, BPartnerGroupDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public BPartnerGroupDto convertEntityToDto(BPartnerGroup partnerGroup, Class<BPartnerGroupDto> dto) {
		if(partnerGroup == null){
			return null;
		}
		BPartnerGroupDto partnerGroupDto = new BPartnerGroupDto();
		partnerGroupDto.setCode(partnerGroup.getCode());
		partnerGroupDto.setName(partnerGroup.getName());
		partnerGroupDto.setbPartnerGroupId(partnerGroup.getbPartnerGroupId());
		partnerGroupDto.setCreated(partnerGroup.getCreated());
		partnerGroupDto.setUpdated(partnerGroup.getUpdated());
		partnerGroupDto.setIsActive(partnerGroup.getIsActive());
		partnerGroupDto.setDescription(partnerGroup.getDescription());
	/*	partnerGroupDto.setPartner(getParter(partnerGroup));*/
		
		return partnerGroupDto;
	}
	
	private BPartnerDto getParter(BPartnerGroup partnerGroup){
		Bpartner partner =partnerGroup.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}

}

