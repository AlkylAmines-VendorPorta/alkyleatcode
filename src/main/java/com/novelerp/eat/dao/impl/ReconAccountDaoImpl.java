package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ReconAccountDao;
import com.novelerp.eat.dto.ReconAccountDto;
import com.novelerp.eat.entity.ReconAccount;

@Repository
public class ReconAccountDaoImpl extends AbstractJpaDAO<ReconAccount, ReconAccountDto> implements ReconAccountDao{ 
	@PostConstruct
	public void postConstruct() {
		setClazz(ReconAccount.class, ReconAccountDto.class);
	}

}
