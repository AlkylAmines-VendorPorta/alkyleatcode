package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.TAHDRApprovalMatrixDto;
import com.novelerp.eat.entity.TAHDRApprovalMatrix;

public interface TAHDRApprovalMatrixDao extends CommonDao<TAHDRApprovalMatrix, TAHDRApprovalMatrixDto>{

	public Long getLevelsfromTahdrId(Long tahdrId);
	
	public int updateStatusOfCurrentUser(Long tahdrId,Long userId,String remarks,String status);
	
	public int updateStatusOfNextUser(Long tahdrId,Long userId);
	public int updateStatusOfFirstUserIP(Long tahdrId);
	int updateStatusOfUserToNull(Long tahdrId);
	public int updateLevelOnDelete(Long tahdrId,Long levels);
	public String getFirstLevelApprovalData();
	public String getNextUserToForMail();
}
