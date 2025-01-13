package com.novelerp.alkyl.dao;

import com.novelerp.alkyl.dto.AdvancePaymentDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.PaymentReadDto;
import com.novelerp.alkyl.entity.AdvancePayment;
import com.novelerp.alkyl.entity.AdvanceShipmentNotice;
import com.novelerp.core.dao.CommonDao;

public interface AdvancePaymentDao extends CommonDao<AdvancePayment, AdvancePaymentDto> {

	
	public String getAllpaymentdetails();

	public String getPaymentReportlist(PaymentReadDto dto);
}
