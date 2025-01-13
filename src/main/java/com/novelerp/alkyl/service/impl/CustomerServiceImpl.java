package com.novelerp.alkyl.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.alkyl.dao.CustomerDao;
import com.novelerp.alkyl.dto.CustomerDto;
import com.novelerp.alkyl.entity.Customer;
import com.novelerp.alkyl.service.CustomerService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class CustomerServiceImpl extends AbstractContextServiceImpl<Customer, CustomerDto> implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(CustomerServiceImpl.class, customerDao, Customer.class, CustomerDto.class);
		setByPassProxy(true);
	}
}
