package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.PaymentGatewayResponseDao;
import com.novelerp.eat.dto.PaymentGatewayResponseDto;
import com.novelerp.eat.entity.PaymentGatewayResponse;

@Repository
public class PaymentGatewayResponseDaoImpl extends AbstractJpaDAO<PaymentGatewayResponse, PaymentGatewayResponseDto> implements PaymentGatewayResponseDao{
	@PostConstruct
	public void postConstruct() {
		setClazz(PaymentGatewayResponse.class, PaymentGatewayResponseDto.class);
	}
	
	@Override
	public String getQueryForDocNo(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT a from PaymentGatewayResponse a where a.docNo=:docNo");
		return query.toString();
	}
}
