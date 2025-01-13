package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.BankBranchDetailsDao;
import com.novelerp.appbase.master.dto.BankBranchDetailsDto;
import com.novelerp.appbase.master.entity.BankBranchDetails;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Ankita Tirodkar
 *
 */
@Repository
public class BankBranchDetailsDaoImpl extends AbstractJpaDAO<BankBranchDetails, BankBranchDetailsDto> implements BankBranchDetailsDao{

	@PostConstruct
	private void init() {
		setClazz(BankBranchDetails.class, BankBranchDetailsDto.class);
	}
	
	public String getBankBranchFromBankNameId(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT branch FROM BankBranchDetails branch ")
		.append(" LEFT JOIN FETCH branch.bankName bk")
		.append(" WHERE bk.bankNameDetailsId =:bankNameDetailsId ORDER BY branch.branchName" );
		return query.toString();
	}
	
}