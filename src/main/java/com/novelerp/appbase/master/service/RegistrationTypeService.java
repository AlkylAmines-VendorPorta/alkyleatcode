package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.RegistrationTypeDto;
import com.novelerp.appbase.master.entity.RegistrationType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface RegistrationTypeService extends CommonService<RegistrationType, RegistrationTypeDto> {

	public List<RegistrationTypeDto> getRegistrationTypeList();
	public RegistrationTypeDto getRegistrationType(Long partnerId);
	/*public ResponseDto addRegistrationType(RegistrationTypeDto dto);
	public ResponseDto editRegistrationType(RegistrationTypeDto dto);
	public ResponseDto deleteRegistrationType(Long id);*/
}
