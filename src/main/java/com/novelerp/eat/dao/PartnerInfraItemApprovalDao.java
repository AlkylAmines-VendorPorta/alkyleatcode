package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.PartnerInfraItemApprovalDto;
import com.novelerp.eat.entity.PartnerInfraItemApproval;

public interface PartnerInfraItemApprovalDao extends CommonDao<PartnerInfraItemApproval, PartnerInfraItemApprovalDto>{

	public int saveInfraApprovalLine(String status,String comment,Long infraItemId,Long partnerId,String roleName);
	
	public int resetInfraApprovalLine(Long infraItemId,Long partnerId);
}
