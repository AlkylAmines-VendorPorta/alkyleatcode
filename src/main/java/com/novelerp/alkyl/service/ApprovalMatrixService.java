package com.novelerp.alkyl.service;

import com.novelerp.alkyl.dto.ApprovalMatrixDto;
import com.novelerp.alkyl.entity.ApprovalMatrix;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.service.CommonService;

public interface ApprovalMatrixService extends CommonService<ApprovalMatrix, ApprovalMatrixDto> {

	public ApprovalMatrixDto approveVendor(ApprovalMatrixDto dto);

	public Errors fetchVendorData() throws Exception;

	public ApprovalMatrixDto approveVendorWithUserDto(ApprovalMatrixDto dto,UserDto user);

	public void updateStatusAfterVendorApproval(ApprovalMatrixDto dto);
	
	
}
