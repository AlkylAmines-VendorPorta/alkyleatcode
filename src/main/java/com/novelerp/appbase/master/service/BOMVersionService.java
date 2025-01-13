package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.BomVersionDto;
import com.novelerp.appbase.master.entity.BOMVersion;
import com.novelerp.core.service.CommonService;

/**
 *  @author Ankita
  */
public interface BOMVersionService extends CommonService<BOMVersion, BomVersionDto>{
	public boolean deleteBomVersion(Long id);
}
