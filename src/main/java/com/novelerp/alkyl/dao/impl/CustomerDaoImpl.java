package com.novelerp.alkyl.dao.impl;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.CustomerDao;
import com.novelerp.alkyl.dto.CustomerDto;
import com.novelerp.alkyl.entity.Customer;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class CustomerDaoImpl extends AbstractJpaDAO<Customer, CustomerDto> implements CustomerDao{

	@Override
	public void postConstruct() {
		setClazz(Customer.class, CustomerDto.class);
	}
	
	public String queryToGetCustomerByType(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM Customer A ")
		.append(" WHERE A.customerType=:customerType and A.isActive='Y' ");
		return jpql.toString();
	}
	
	
}
