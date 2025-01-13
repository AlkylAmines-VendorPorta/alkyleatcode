package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appbase.master.entity.GtpParameter;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface GtpParameterService extends CommonService<GtpParameter, GtpParameterDto> {

	public List<GtpParameterDto> getGtpParameterList();
	public GtpParameterDto getGtpParameter(Long partnerId);
	/*public CustomResponseDto saveGtpParameter(GtpParameterDto dto);
	public CustomResponseDto editGtpParameter(GtpParameterDto dto);*/
	/*public CustomResponseDto deleteGtpParameter(Long id);*/
	public com.novelerp.core.dto.CustomResponseDto copyGTP(List<GtpParameterDto> gtpList,Long materialId);
	public com.novelerp.core.dto.CustomResponseDto deleteGTP(Long materialId);
	public List<GtpParameterDto> getGtpParameterList(int pageNumber, int pageSize, String searchColumn, String SearchValue,Long id);
	public Long getGtpParameterCount(String searchColumn, String searchValue,Long id);
	public boolean deleteGtpParameter(Long id);
}