package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.TaxCategoryDto;
import com.novelerp.appbase.master.entity.TaxCategory;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Aman
 *
 */
public interface TaxCategoryService extends CommonService<TaxCategory, TaxCategoryDto> {

	public List<TaxCategoryDto> getTaxCategoryList(Long entityId);
	public TaxCategoryDto getTaxCategory(Long partnerId);
	public boolean deleteTaxCat(Long id);
	/*public ResponseDto addTaxCategory(TaxCategoryDto dto);
	public ResponseDto editTaxCategory(TaxCategoryDto dto);
	public ResponseDto deleteTaxCategory(Long id);*/
}
