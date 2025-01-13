package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.HolderTypeDto;
import com.novelerp.appbase.master.entity.HolderType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface HolderTypeService extends CommonService<HolderType, HolderTypeDto> {

	public List<HolderTypeDto> getHolderTypeList();
	public HolderTypeDto getHolderType(Long partnerId);
	/*public ResponseDto addHolderType(HolderTypeDto dto);
	public ResponseDto editHolderType(HolderTypeDto dto);
	public ResponseDto deleteHolderType(Long id);*/
}