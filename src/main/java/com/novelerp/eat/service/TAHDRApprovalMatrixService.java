package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.entity.TAHDRApprovalMatrix;

public interface TAHDRApprovalMatrixService extends CommonService<TAHDRApprovalMatrix, TAHDRApprovalMatrixDto>  {

	public List<TAHDRApprovalMatrixDto> getUserNameForTenderList(String userName);
	/*public TAHDRApprovalMatrixDto setStatusApprove(Long tahdrId,String status);*/
	/**
	 * @param tahdrId
	 * @param approvalMatrixRow
	 * @return
	 */
	public TAHDRApprovalMatrixDto getRowForUpdateApprovalMatrix(Long tahdrId, Long approvalMatrixRow);
	/**
	 * @param tahdrId
	 * @return
	 */
	public Long getRowForUpdateApprovalMatrixLevel(Long tahdrId);
	/**
	 * @param approvalMatrixDto
	 * @return
	 */
	public int setStatusInProgress(Long id);
	/**
	 * @param tahdrId
	 * @return
	 */
	public TAHDRApprovalMatrixDto getApprovalMatrixForAP(Long tahdrId);
	/**
	 * @param approvalMatrixDto
	 * @param status
	 * @return
	 */
	public TAHDRApprovalMatrixDto setStatusApprove(TAHDRApprovalMatrixDto approvalMatrixDto, String status);
	public int updateLevelOnDelete(Long tahdrId,Long levels);
}
