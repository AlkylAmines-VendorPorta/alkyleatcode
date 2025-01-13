package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.master.entity.InfraApprovalLevel;
import com.novelerp.core.service.CommonService;

public interface InfraApprovalLevelService extends CommonService<InfraApprovalLevel, InfraApprovalLevelDto>{
	
	public boolean isRoleAuthorise(String roleName);

}
