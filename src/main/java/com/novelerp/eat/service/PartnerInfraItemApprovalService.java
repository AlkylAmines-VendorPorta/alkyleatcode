package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.PartnerInfraItemApprovalDto;
import com.novelerp.eat.entity.PartnerInfraItemApproval;

public interface PartnerInfraItemApprovalService extends CommonService<PartnerInfraItemApproval, PartnerInfraItemApprovalDto> {
	
	public CustomResponseDto createInfraItemLine(List<InfraApprovalLevelDto> infraApprovalLevels,PartnerInfraItemDto partnerInfraItem);
	
	public PartnerInfraItemApprovalDto savePartnerInfraItemApprovalLine(PartnerInfraItemApprovalDto partnerInfraItemApprovalDto);
	
	public CustomResponseDto uploadClarification(PartnerInfraItemApprovalDto partnerInfraItemApprovalDto);
	
	public ResponseDto saveInfraApprovalLine( PartnerInfraItemApprovalDto partnerInfraItemApprovalDto,String roleName);
	
	public boolean resetApprovalLine(PartnerInfraItemApprovalDto partnerInfraItemApprovalDto);
	
	public boolean sendInfraApprovalMail(PartnerInfraItemApprovalDto latestApproval);
	
	public CustomResponseDto submitInfraItem();
}
