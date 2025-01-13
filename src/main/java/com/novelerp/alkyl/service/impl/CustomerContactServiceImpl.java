package com.novelerp.alkyl.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.alkyl.dao.CustomerContactDao;
import com.novelerp.alkyl.dto.CustomerContactDto;
import com.novelerp.alkyl.entity.CustomerContact;
import com.novelerp.alkyl.service.CustomerContactService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class CustomerContactServiceImpl extends AbstractContextServiceImpl<CustomerContact, CustomerContactDto> implements CustomerContactService{

	@Autowired
	private CustomerContactDao customerContactDao;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(CustomerContactServiceImpl.class, customerContactDao, CustomerContact.class, CustomerContactDto.class);
		setByPassProxy(true);
	}
}
