package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.TenderTypeDto;
import com.novelerp.appbase.master.entity.TenderType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface TenderTypeService extends CommonService<TenderType, TenderTypeDto> {

	public List<TenderTypeDto> getTenderTypeList();
	public TenderTypeDto getTenderType(Long partnerId);
	/*public ResponseDto addTenderType(TenderTypeDto dto);
	public ResponseDto editTenderType(TenderTypeDto dto);
	public ResponseDto deleteTenderType(Long id);*/
}
