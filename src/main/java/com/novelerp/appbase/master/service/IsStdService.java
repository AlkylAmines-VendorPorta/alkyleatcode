package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.IsStdDto;
import com.novelerp.appbase.master.entity.IsStd;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface IsStdService extends CommonService<IsStd, IsStdDto> {

	public List<IsStdDto> getIsStdList();
	public IsStdDto getIsStd(Long partnerId);
	/*public ResponseDto addIsStd(IsStdDto dto);
	public ResponseDto editIsStd(IsStdDto dto);
	public ResponseDto deleteIsStd(Long id);*/
}
