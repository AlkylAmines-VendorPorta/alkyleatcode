package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.CompanyTypeDto;
import com.novelerp.appbase.master.entity.CompanyType;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

@Component
public class CompanyTypeConverter extends CustomDozerConverter<CompanyType, CompanyTypeDto> implements ObjectConverter<CompanyType, CompanyTypeDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public CompanyTypeDto convertEntityToDto(CompanyType entity, Class<CompanyTypeDto> dto) {
		if(entity == null){
			return null;
		}
		CompanyTypeDto CompanyTypeDto = new CompanyTypeDto();
		CompanyTypeDto.setCode(entity.getCode());
		CompanyTypeDto.setName(entity.getName());
		CompanyTypeDto.setCompanyTypeId(entity.getCompanyTypeId());
		CompanyTypeDto.setCreated(entity.getCreated());
		CompanyTypeDto.setUpdated(entity.getUpdated());
		CompanyTypeDto.setIsActive(entity.getIsActive());
		CompanyTypeDto.setDescription(entity.getDescription());
		/*CompanyTypeDto.setPartner(getParter(entity));*/
		
		return CompanyTypeDto;
	}
	
	private BPartnerDto getParter(CompanyType entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}


}