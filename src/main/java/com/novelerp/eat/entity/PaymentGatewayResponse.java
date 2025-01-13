package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Akshay
 *
 */
@Entity
@Table(name="t_paymentgateway_response")
public class PaymentGatewayResponse extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	
	private Long paymentGatewayResponseId;
	private Long docNo;
	private String status;
	
	@Id
	@SequenceGenerator(name="t_paymentgateway_response_seq",sequenceName="t_paymentgateway_response_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_paymentgateway_response_seq")	
	@Column(name = "t_paymentgateway_response_id", updatable=false)
	public Long getPaymentGatewayResponseId() {
		return paymentGatewayResponseId;
	}
	public void setPaymentGatewayResponseId(Long paymentGatewayResponseId) {
		this.paymentGatewayResponseId = paymentGatewayResponseId;
	}
	
	@Column(name="doc_no")
	public Long getDocNo() {
		return docNo;
	}
	public void setDocNo(Long docNo) {
		this.docNo = docNo;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
