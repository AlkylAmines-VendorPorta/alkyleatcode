package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appbase.master.entity.Tax;
import com.novelerp.appbase.master.service.TaxCategoryService;
import com.novelerp.appcontext.converter.CustomContextDozerConverter;
import com.novelerp.core.converter.ObjectConverter;
/**
 * @author Aman Sahu
 *
 */


@Component
public class TaxConverter extends CustomContextDozerConverter<Tax, TaxDto> implements ObjectConverter<Tax, TaxDto>{
	
	@Autowired
	private TaxCategoryService taxCategoryService;
	
	@Override
	public TaxDto convertEntityToDto(Tax entity, Class<TaxDto> dto) {
		if(entity == null){
			return null;
		}
		TaxDto taxDto = new TaxDto();
		taxDto.setCode(entity.getCode());
		taxDto.setName(entity.getName());
		taxDto.setTaxId(entity.getTaxId());
		taxDto.setDescription(entity.getDescription());
		taxDto.setTaxcategory(taxCategoryService.getDto(entity.getTaxcategory(), null));
		return taxDto;
	}
}
