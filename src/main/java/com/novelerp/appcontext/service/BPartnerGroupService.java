package com.novelerp.appcontext.service;

import java.util.List;

import com.novelerp.appcontext.dto.BPartnerGroupDto;
import com.novelerp.appcontext.entity.BPartnerGroup;
import com.novelerp.core.service.CommonService;

public interface BPartnerGroupService extends CommonService<BPartnerGroup, BPartnerGroupDto> {

	public List<BPartnerGroupDto> getBusinessPartnerGroupList();
	public BPartnerGroupDto getBusinessPartnerGroup(Long id);
}

