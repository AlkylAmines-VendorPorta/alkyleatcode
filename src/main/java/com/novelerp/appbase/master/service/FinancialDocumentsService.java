package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.FinancialDocumentsDto;
import com.novelerp.appbase.master.entity.FinancialDocuments;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface FinancialDocumentsService extends CommonService<FinancialDocuments, FinancialDocumentsDto> {

	public List<FinancialDocumentsDto> getFinancialDocumentsList();
	public FinancialDocumentsDto getFinancialDocuments(Long id);
}