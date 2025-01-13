package com.novelerp.appcontext.service;

import com.novelerp.appcontext.dto.OfficeLocationDto;
import com.novelerp.appcontext.entity.OfficeLocation;
import com.novelerp.core.service.CommonService;

public interface OfficeLocationService extends CommonService<OfficeLocation, OfficeLocationDto>{

	boolean deleteReferenceList(Long officeTypeId);

}
