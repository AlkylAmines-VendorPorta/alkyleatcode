/**
 * @author Ankush
 */
package com.novelerp.appbase.master.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.novelerp.appbase.master.dto.TaxCategoryDto;
import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appbase.master.entity.Tax;
import com.novelerp.core.converter.CustomDozerConverter;
import com.novelerp.core.converter.ObjectConverter;

public class TaxWithCategoryConverter extends CustomDozerConverter<Tax, TaxDto>
		implements ObjectConverter<Tax, TaxDto> {

	@Autowired
	private TaxConverter taxConverter;

	@Autowired
	private TaxCategoryConverter taxCategoryConverter;
	
	@Override
	public TaxDto convertEntityToDto(Tax entity, Class<TaxDto> dtoClass) {
		if(entity==null){
			return null;
		}
		
		TaxDto dto=new TaxDto();
		dto=taxConverter.convertEntityToDto(entity, TaxDto.class);
		dto.setTaxcategory(taxCategoryConverter.convertEntityToDto(entity.getTaxcategory(), TaxCategoryDto.class));
		return dto;
	}

}
