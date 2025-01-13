package com.novelerp.eat.dao;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.PaymentGatewayResponseDto;
import com.novelerp.eat.entity.PaymentGatewayResponse;

public interface PaymentGatewayResponseDao extends CommonDao<PaymentGatewayResponse, PaymentGatewayResponseDto>{

	public String getQueryForDocNo();

}
