package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.TenderBudgetTypeDto;
import com.novelerp.appbase.master.entity.TenderBudgetType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface TenderBudgetTypeService extends CommonService<TenderBudgetType, TenderBudgetTypeDto> {

	public List<TenderBudgetTypeDto> getTenderBudgetTypeList();
	public TenderBudgetTypeDto getTenderBudgetType(Long partnerId);
	/*public ResponseDto addTenderBudgetType(TenderBudgetTypeDto dto);
	public ResponseDto editTenderBudgetType(TenderBudgetTypeDto dto);
	public ResponseDto deleteTenderBudgetType(Long id);*/
}
