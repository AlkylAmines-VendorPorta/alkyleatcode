package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.HSNDto;
import com.novelerp.appbase.master.entity.HSN;
import com.novelerp.core.service.CommonService;

/**
 *  @author Ankita
  */
public interface HSNService extends CommonService<HSN, HSNDto> {
	public boolean deleteHSN(Long id);
}
