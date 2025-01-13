package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.ExemptedCategoriesDto;
import com.novelerp.appbase.master.entity.ExemptedCategories;
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
public class ExemptedCategoriesConverter extends CustomDozerConverter<ExemptedCategories, ExemptedCategoriesDto> implements ObjectConverter<ExemptedCategories, ExemptedCategoriesDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public ExemptedCategoriesDto convertEntityToDto(ExemptedCategories entity, Class<ExemptedCategoriesDto> dto) {
		if(entity == null){
			return null;
		}
		ExemptedCategoriesDto bidTypeDto = new ExemptedCategoriesDto();
		bidTypeDto.setCode(entity.getCode());
		bidTypeDto.setName(entity.getName());
		bidTypeDto.setExemptedCategoriesId(entity.getExemptedCategoriesId());
		bidTypeDto.setCreated(entity.getCreated());
		bidTypeDto.setUpdated(entity.getUpdated());
		bidTypeDto.setIsActive(entity.getIsActive());
		bidTypeDto.setDescription(entity.getDescription());
		/*bidTypeDto.setPartner(getParter(entity));*/
		
		return bidTypeDto;
	}
	
	private BPartnerDto getParter(ExemptedCategories entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}


