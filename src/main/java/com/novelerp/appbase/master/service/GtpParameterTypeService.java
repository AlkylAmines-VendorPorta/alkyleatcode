package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.GtpParameterTypeDto;
import com.novelerp.appbase.master.entity.GtpParameterType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface GtpParameterTypeService extends CommonService<GtpParameterType, GtpParameterTypeDto> {

	public List<GtpParameterTypeDto> getGtpParameterTypeList();
	public GtpParameterTypeDto getGtpParameterType(Long partnerId);
	public CustomResponseDto saveGtpParameterType(GtpParameterTypeDto dto);
	public CustomResponseDto editGtpParameterType(GtpParameterTypeDto dto);
	public CustomResponseDto deleteGtpParameterType(Long id);
}
