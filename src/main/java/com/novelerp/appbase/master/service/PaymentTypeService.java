package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.entity.PaymentType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface PaymentTypeService extends CommonService<PaymentType, PaymentTypeDto> {

	public List<PaymentTypeDto> getPaymentTypeList();
	public PaymentTypeDto getPaymentType(Long partnerId);
	public List<PaymentTypeDto> getPaymentTypeForPartner();
	/*public ResponseDto addPaymentType(PaymentTypeDto dto);
	public ResponseDto editPaymentType(PaymentTypeDto dto);
	public ResponseDto deletePaymentType(Long id);*/
}
