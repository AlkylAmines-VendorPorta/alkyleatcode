package com.novelerp.alkyl.service;

import com.novelerp.alkyl.dto.VendorProfileHistoryDto;
import com.novelerp.alkyl.entity.VendorProfileHistory;
import com.novelerp.core.service.CommonService;

public interface VendorProfileHistoryService extends CommonService<VendorProfileHistory, VendorProfileHistoryDto>{

	void saveDto(VendorProfileHistoryDto dto);
	
	

}
