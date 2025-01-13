package com.novelerp.alkyl.service;


import java.util.List;

import com.novelerp.alkyl.dto.AdvancePaymentDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PaymentReadDto;
import com.novelerp.alkyl.entity.AdvancePayment;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.CommonService;

public interface AdvancePaymentService extends CommonService<AdvancePayment, AdvancePaymentDto>{

//	AdvancePaymentDto saveadvancepayment(AdvancePaymentDto advance);

	List<AdvancePaymentDto> saveadvancepayment(List<AdvancePaymentDto> advance);

	void sendMailforApproval(List<AdvancePaymentDto> advance);
	
	void sendProcessedDatatoSAP(List<AdvancePaymentDto> advance);

	List<AdvancePaymentDto> getPaymentReportbyFilter(PaymentReadDto dto);

	CustomResponseDto sendApprovedMailtoVendor(List<AdvancePaymentDto> advance);

}
