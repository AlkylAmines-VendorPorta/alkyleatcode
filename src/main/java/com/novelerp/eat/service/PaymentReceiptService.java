package com.novelerp.eat.service;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.entity.PaymentDetail;

/**
 * 
 * @author Ankita
 *
 */
public interface PaymentReceiptService extends CommonService<PaymentDetail, PaymentDetailDto>{

	public PaymentDetailDto getPaymentDetailsByPaymentType(Long id, String paymentType);
}
