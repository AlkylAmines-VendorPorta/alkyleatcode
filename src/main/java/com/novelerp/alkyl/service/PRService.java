package com.novelerp.alkyl.service;

import java.util.List;

import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRReadDto;
import com.novelerp.alkyl.entity.PR;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

public interface PRService extends CommonService<PR, PRDto> {

	public PRDto updatePRSubmit(PRDto prDto);

	public PRDto updatePRApprove(PRDto prDto);
	
	public PRDto updatePRBuyerAssign(PRDto prDto);

	public ResponseDto updatePRReject(Long prId, String remark);

	public void generateQCF(Long prId);

	public List<Object> getQCFReport(Long prId);

	public List<PRDto> getPRByFilter(PRReadDto dto);

	public PRDto updatePRSave(PRDto prDto);
	
}
