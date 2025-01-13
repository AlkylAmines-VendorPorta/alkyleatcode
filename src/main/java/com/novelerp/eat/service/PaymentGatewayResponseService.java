package com.novelerp.eat.service;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.PaymentGatewayResponseDto;
import com.novelerp.eat.entity.PaymentGatewayResponse;

public interface PaymentGatewayResponseService extends CommonService<PaymentGatewayResponse, PaymentGatewayResponseDto>{
	
	public PaymentGatewayResponseDto getPaymentGatewayResponseByDocNo(String docNo);

}
