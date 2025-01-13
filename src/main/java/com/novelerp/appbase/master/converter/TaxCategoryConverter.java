package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.TaxCategoryDto;
import com.novelerp.appbase.master.entity.TaxCategory;
import com.novelerp.appcontext.converter.PartnerConverter;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

/**
 * 
 * 
 * @author Aman Sahu
 *
 */
@Component
public class TaxCategoryConverter extends CustomDozerConverter<TaxCategory, TaxCategoryDto> implements ObjectConverter<TaxCategory, TaxCategoryDto>{

	@Autowired
	private PartnerConverter partnerConverter;
	
	@Override
	public TaxCategoryDto convertEntityToDto(TaxCategory entity, Class<TaxCategoryDto> dto) {
		if(entity == null){
			return null;
		}
		TaxCategoryDto TaxCategoryDto = new TaxCategoryDto();
		TaxCategoryDto.setCode(entity.getCode());
		TaxCategoryDto.setName(entity.getName());
		TaxCategoryDto.setTaxCategoryId(entity.getTaxCategoryId());
		TaxCategoryDto.setCreated(entity.getCreated());
		TaxCategoryDto.setUpdated(entity.getUpdated());
		TaxCategoryDto.setIsActive(entity.getIsActive());
		TaxCategoryDto.setDescription(entity.getDescription());
		return TaxCategoryDto;
	}
	
	private BPartnerDto getParter(TaxCategory entity){
		Bpartner partner =entity.getPartner();
		if(partner != null){
			partner.setLoadDefault(false);
		}
		return partnerConverter.convertEntityToDto(partner, BPartnerDto.class);
	}



}

