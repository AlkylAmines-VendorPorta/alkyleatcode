package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dao.PaymentGatewayResponseDao;
import com.novelerp.eat.dto.PaymentGatewayResponseDto;
import com.novelerp.eat.entity.PaymentGatewayResponse;
import com.novelerp.eat.service.PaymentGatewayResponseService;

@Service
public class PaymentGatewayResponseServiceImpl
		extends AbstractServiceImpl<PaymentGatewayResponse, PaymentGatewayResponseDto>
		implements PaymentGatewayResponseService {

	@Autowired
	private PaymentGatewayResponseDao paymentGatewayResponseDao;

	@PostConstruct
	public void init() {
		super.init(PaymentGatewayResponseServiceImpl.class, paymentGatewayResponseDao, PaymentGatewayResponse.class,
				PaymentGatewayResponseDto.class);
		setByPassProxy(true);
	}

	@Override
	public PaymentGatewayResponseDto getPaymentGatewayResponseByDocNo(String docNo) {
		Map<String, Object> param = new HashMap<>();
		param.put("docNo", Long.parseLong(docNo));
		String query = paymentGatewayResponseDao.getQueryForDocNo();
		return findDtoByQuery(query, param);
	}
}
