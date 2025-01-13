package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.VendorTypeDto;
import com.novelerp.appbase.master.entity.VendorType;
import com.novelerp.core.service.CommonService;

public interface VendorTypeService extends CommonService<VendorType, VendorTypeDto> {

	public List<VendorTypeDto> getVendorTypeList();
	public VendorTypeDto getVendorType(Long id);
}
