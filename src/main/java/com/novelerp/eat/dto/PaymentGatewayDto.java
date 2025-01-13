package com.novelerp.eat.dto;

import java.math.BigDecimal;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * Ankita Tirodkar
 */
public class PaymentGatewayDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long systemId;
	private Long requestId;
	private BigDecimal txnAmt;
	private String email;
	private String mobileNo;
	private String udf;
	private String payeeName;
	private String tenderNo;
	private String paymentType;
	private String redirectUrl;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public BigDecimal getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(BigDecimal txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getUdf() {
		return udf;
	}
	public void setUdf(String udf) {
		this.udf = udf;
	}
	
	@Override
    public String toString() {
        return "msg [request_id=" + requestId + " | txn_amt=" + txnAmt + " | email=" + email + " | mob=" + mobileNo + " | udf=" + udf + "]";
    }
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
