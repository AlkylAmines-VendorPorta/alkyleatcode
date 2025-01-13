package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.BankBranchDetailsDao;
import com.novelerp.appbase.master.dto.BankBranchDetailsDto;
import com.novelerp.appbase.master.entity.BankBranchDetails;
import com.novelerp.appbase.master.service.BankBranchDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
/** 
 * @author Ankita
 *
 */
@Service
public class BankBranchDetailsServiceImpl extends AbstractContextServiceImpl<BankBranchDetails, BankBranchDetailsDto> implements BankBranchDetailsService {
	@Autowired
	private BankBranchDetailsDao bankBranchDetailsDao;
	
	@PostConstruct
	private void init() {
		super.init(BankBranchDetailsServiceImpl.class, bankBranchDetailsDao, BankBranchDetails.class, BankBranchDetailsDto.class);
		setByPassProxy(true);
	}
}
