package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.BPartnerMapDto;
import com.novelerp.appbase.master.entity.BPartnerMap;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.service.CommonService;

public interface SendRequestToParticipantService extends CommonService<BPartnerMap, BPartnerMapDto> {
	public BPartnerMapDto saveRequestDetails(BPartnerDto bpartnerdto,UserDto userdto);
}
