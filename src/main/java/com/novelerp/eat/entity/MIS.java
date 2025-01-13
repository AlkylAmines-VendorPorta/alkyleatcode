package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name = "t_mis")
public class MIS extends ContextPO {
	
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
	
	
	@Id
	@SequenceGenerator(name="t_mis_seq",sequenceName="t_mis_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_mis_seq")	
	@Column(name = "t_mis_id", updatable=false)
	public Long getMisId() {
		return misId;
	}
	public void setMisId(Long misId) {
		this.misId = misId;
	}
	
	@Column(name = "doc_no", updatable=false)
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	
	@Column(name = "charges", updatable=false)
	public Double getCharges() {
		return charges;
	}
	public void setCharges(Double charges) {
		this.charges = charges;
	}
	
	@Column(name = "biller_id")
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	
	@Column(name = "bank_id")
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Column(name = "bank_ref_no")
	public String getBankRefNo() {
		return bankRefNo;
	}
	public void setBankRefNo(String bankRefNo) {
		this.bankRefNo = bankRefNo;
	}
	
	@Column(name = "pgi_ref_no")
	public String getpGIRefNo() {
		return pGIRefNo;
	}
	public void setpGIRefNo(String pGIRefNo) {
		this.pGIRefNo = pGIRefNo;
	}
	
	@Column(name = "fee_type")
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name = "tender_no")
	public String getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}
	
	@Column(name = "ref_5")
	public String getRef5() {
		return ref5;
	}
	public void setRef5(String ref5) {
		this.ref5 = ref5;
	}
	
	@Column(name = "ref_6")
	public String getRef6() {
		return ref6;
	}
	public void setRef6(String ref6) {
		this.ref6 = ref6;
	}
	
	@Column(name = "ref_7")
	public String getRef7() {
		return ref7;
	}
	public void setRef7(String ref7) {
		this.ref7 = ref7;
	}
	
	@Column(name = "ref_8")
	public String getRef8() {
		return ref8;
	}
	public void setRef8(String ref8) {
		this.ref8 = ref8;
	}
	
	@Column(name = "filler")
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	
	@Column(name = "date_of_txn")
	public String getDateofTxn() {
		return dateofTxn;
	}
	public void setDateofTxn(String dateofTxn) {
		this.dateofTxn = dateofTxn;
	}
	
	@Column(name = "settlement_date")
	public String getSettlementdate() {
		return settlementdate;
	}
	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate;
	}
	
	@Column(name = "gross_mount")
	public String getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(String grossAmount) {
		this.grossAmount = grossAmount;
	}
	
	@Column(name = "gst")
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	
	@Column(name = "net_amount")
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	
}
