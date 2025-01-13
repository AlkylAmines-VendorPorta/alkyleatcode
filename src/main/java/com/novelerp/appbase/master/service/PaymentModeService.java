package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.PaymentModeDto;
import com.novelerp.appbase.master.entity.PaymentMode;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface PaymentModeService extends CommonService<PaymentMode, PaymentModeDto> {

	public List<PaymentModeDto> getPaymentModeList();
	public PaymentModeDto getPaymentMode(Long partnerId);
	/*public ResponseDto addPaymentMode(PaymentModeDto dto);
	public ResponseDto editPaymentMode(PaymentModeDto dto);
	public ResponseDto deletePaymentMode(Long id);*/
}
