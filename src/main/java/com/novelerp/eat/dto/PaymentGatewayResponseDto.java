package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class PaymentGatewayResponseDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long paymentGatewayResponseId;
	private Long docNo;
	private String status;
	
	
	public Long getPaymentGatewayResponseId() {
		return paymentGatewayResponseId;
	}
	public void setPaymentGatewayResponseId(Long paymentGatewayResponseId) {
		this.paymentGatewayResponseId = paymentGatewayResponseId;
	}
	public Long getDocNo() {
		return docNo;
	}
	public void setDocNo(Long docNo) {
		this.docNo = docNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
