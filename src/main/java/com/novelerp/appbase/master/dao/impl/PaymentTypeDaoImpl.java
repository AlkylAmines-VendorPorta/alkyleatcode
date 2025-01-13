package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PaymentTypeDao;
import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.entity.PaymentType;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class PaymentTypeDaoImpl extends AbstractJpaDAO<PaymentType, PaymentTypeDto> implements PaymentTypeDao{


	
	@PostConstruct
	private void init() {
		setClazz(PaymentType.class, PaymentTypeDto.class);
	}
	

	@Override
	public String getPaymentTypeByCode(){
		StringBuilder query= new StringBuilder();
		query.append(" SELECT pt FROM PaymentType pt ");
		query.append(" LEFT JOIN FETCH pt.partner bp ");
		query.append(" WHERE pt.code=:paymentTypeCode ");
		return query.toString();
	}
	
	public String getPaymentTypeById(){
		StringBuilder query= new StringBuilder();
		query.append(" SELECT pt FROM PaymentType pt ");
		query.append(" LEFT JOIN FETCH pt.partner bp ");
		query.append(" WHERE pt.paymentTypeId=:paymentTypeId ");
		return query.toString();
	}
	
    public String getQueryForVenderPayment()
    {
    	StringBuilder query= new StringBuilder();
		query.append(" SELECT pt FROM PaymentType pt ");
		query.append(" where pt.code='RG' ");
		return query.toString();
    }
}
