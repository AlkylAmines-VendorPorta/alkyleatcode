package com.novelerp.eat.service;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.SapVendorDetailDto;
import com.novelerp.eat.entity.SapVendorDetail;

public interface SapVendorDetailService extends CommonService<SapVendorDetail, SapVendorDetailDto>{

	public SapVendorDetailDto setDtoDetail(SapVendorDetailDto sapVendorDto);

}
