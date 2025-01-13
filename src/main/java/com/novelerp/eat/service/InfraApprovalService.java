package com.novelerp.eat.service;

import com.novelerp.core.dto.CustomResponseDto;

public interface InfraApprovalService {
	
	public CustomResponseDto levelwiseApproval(String roleName);
	
	public CustomResponseDto loadVendor();
	
	/*public CustomResponseDto setInfraItemLine(PartnerInfraItemApprovalDto partnerInfraItemApproval);*/

}
