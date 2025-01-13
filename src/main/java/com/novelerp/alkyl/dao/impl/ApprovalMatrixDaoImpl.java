package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.ApprovalMatrixDao;
import com.novelerp.alkyl.dto.ApprovalMatrixDto;
import com.novelerp.alkyl.entity.ApprovalMatrix;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class ApprovalMatrixDaoImpl extends AbstractJpaDAO<ApprovalMatrix, ApprovalMatrixDto> implements ApprovalMatrixDao{

	@PostConstruct
	public void postConstruct() {
		setClazz(ApprovalMatrix.class, ApprovalMatrixDto.class);
	}
	
	public String getApprovalMatrix(){
		StringBuilder sb= new StringBuilder()
				.append(" SELECT A FROM ApprovalMatrix A ")
				.append(" INNER JOIN A.partner B ")
				.append(" WHERE B.bPartnerId=:partnerId ");
		return sb.toString();
	}
}
