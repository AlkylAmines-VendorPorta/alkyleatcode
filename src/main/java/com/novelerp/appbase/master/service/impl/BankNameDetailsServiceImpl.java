package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.master.dao.BankNameDetailsDao;
import com.novelerp.appbase.master.dto.BankNameDetailsDto;
import com.novelerp.appbase.master.entity.BankNameDetails;
import com.novelerp.appbase.master.service.BankNameDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

/** 
 * @author Ankita
 *
 */
@Service
public class BankNameDetailsServiceImpl extends AbstractContextServiceImpl<BankNameDetails, BankNameDetailsDto> implements BankNameDetailsService {
	@Autowired
	private BankNameDetailsDao bankNameDetailsDao;
	
	@PostConstruct
	private void init() {
		super.init(BankNameDetailsServiceImpl.class, bankNameDetailsDao, BankNameDetails.class, BankNameDetailsDto.class);
		setByPassProxy(true);
	}

}
