package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.BankNameDetailsDao;
import com.novelerp.appbase.master.dto.BankNameDetailsDto;
import com.novelerp.appbase.master.entity.BankNameDetails;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Ankita Tirodkar
 *
 */
@Repository
public class BankNameDetailsDaoImpl extends AbstractJpaDAO<BankNameDetails, BankNameDetailsDto> implements BankNameDetailsDao{

	@PostConstruct
	private void init() {
		setClazz(BankNameDetails.class, BankNameDetailsDto.class);
	}

	public String getAllBankName(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT distinct(b) FROM BankNameDetails b ")
		.append(" ORDER BY b.name");
		return jpql.toString();
	}
}
