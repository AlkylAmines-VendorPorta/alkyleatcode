package com.novelerp.alkyl.dao.impl;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.CustomerContactDao;
import com.novelerp.alkyl.dto.CustomerContactDto;
import com.novelerp.alkyl.entity.CustomerContact;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class CustomerContactDaoImpl extends AbstractJpaDAO<CustomerContact, CustomerContactDto> implements CustomerContactDao{

	@Override
	public void postConstruct() {
		setClazz(CustomerContact.class, CustomerContactDto.class);
	}
}
