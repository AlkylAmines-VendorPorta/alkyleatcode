package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class MISDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long misId;
	private String docNo;
	private Double charges;
	private String billerId;
	private String bankId;
	private String bankRefNo;
	private String pGIRefNo;
	private String feeType;
	private String companyName;
	private String tenderNo;
	private String ref5;
	private String ref6;
	private String ref7;
	private String ref8;
	private String filler;
	private String dateofTxn;
	private String settlementdate;
	private String grossAmount;
	private String gst;
	private Double netAmount;


	public Long getMisId() {
		return misId;
	}
	public void setMisId(Long misId) {
		this.misId = misId;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public Double getCharges() {
		return charges;
	}
	public void setCharges(Double charges) {
		this.charges = charges;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankRefNo() {
		return bankRefNo;
	}
	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}
	public String getpGIRefNo() {
		return pGIRefNo;
	}
	public void setpGIRefNo(String pGIRefNo) {
		this.pGIRefNo = pGIRefNo;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}
	public String getRef5() {
		return ref5;
	}
	public void setRef5(String ref5) {
		this.ref5 = ref5;
	}
	public String getRef6() {
		return ref6;
	}
	public void setRef6(String ref6) {
		this.ref6 = ref6;
	}
	public String getRef7() {
		return ref7;
	}
	public void setRef7(String ref7) {
		this.ref7 = ref7;
	}
	public String getRef8() {
		return ref8;
	}
	public void setRef8(String ref8) {
		this.ref8 = ref8;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	public String getDateofTxn() {
		return dateofTxn;
	}
	public void setDateofTxn(String dateofTxn) {
		this.dateofTxn = dateofTxn;
	}
	public String getSettlementdate() {
		return settlementdate;
	}
	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate;
	}
	public String getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(String grossAmount) {
		this.grossAmount = grossAmount;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

}
