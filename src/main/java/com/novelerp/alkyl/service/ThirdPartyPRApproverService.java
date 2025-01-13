package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.ThirdPartyPRApproverDto;
import com.novelerp.alkyl.entity.ThirdPartyPRApprover;
import com.novelerp.core.service.CommonService;

public interface ThirdPartyPRApproverService extends CommonService<ThirdPartyPRApprover, ThirdPartyPRApproverDto> {

	public List<ThirdPartyPRApproverDto> save(List<ThirdPartyPRApproverDto> tpPrApp);

}
