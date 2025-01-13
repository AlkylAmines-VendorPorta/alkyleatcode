package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appbase.master.entity.Tax;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface TaxService extends CommonService<Tax, TaxDto> {

	public List<TaxDto> getTaxList();
	public TaxDto getTax(Long partnerId);
	public CustomResponseDto saveTax(TaxDto TaxDto);
	public boolean deleteTax(Long id);
	public CustomResponseDto editTax(TaxDto TaxDto);
	/*public ResponseDto addTax(TaxDto dto);
	public ResponseDto editTax(TaxDto dto);
	public ResponseDto deleteTax(Long id);*/
}
