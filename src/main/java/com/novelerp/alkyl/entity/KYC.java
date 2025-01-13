package com.novelerp.alkyl.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_bp_kyc")
public class KYC extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long kycId;
	private Attachment gst1;
	private Attachment gst2;
	private Attachment gst3;
	private Attachment threeB1;
	private Attachment threeB2;
	private Attachment threeB3;
	private Attachment cancelledCheque;
	private String isMSME;
	private Attachment msmeCertificate;
	private String isLowerDeduction;
	private Attachment lowerDeductionCert;
	private Date validFrom;
	private Date validTo; 
	private String ldValue;
	private String isRelatedParty;
	private String msmeNumber;
	private String msmeType;
	/*private List<OtherDocuments> otherDocuments;*/
	
	@Id
	@SequenceGenerator(name = "m_bp_kyc_seq", sequenceName = "m_bp_kyc_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_bp_kyc_seq")
	@Column(name = "m_bp_kyc_id", updatable = false)
	public Long getKycId() {
		return kycId;
	}
	public void setKycId(Long kycId) {
		this.kycId = kycId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gst1",referencedColumnName="m_attachment_id")
	public Attachment getGst1() {
		return gst1;
	}
	public void setGst1(Attachment gst1) {
		this.gst1 = gst1;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gst2",referencedColumnName="m_attachment_id")
	public Attachment getGst2() {
		return gst2;
	}
	public void setGst2(Attachment gst2) {
		this.gst2 = gst2;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gst3",referencedColumnName="m_attachment_id")
	public Attachment getGst3() {
		return gst3;
	}
	public void setGst3(Attachment gst3) {
		this.gst3 = gst3;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="three_b_1",referencedColumnName="m_attachment_id")
	public Attachment getThreeB1() {
		return threeB1;
	}
	public void setThreeB1(Attachment threeB1) {
		this.threeB1 = threeB1;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="three_b_2",referencedColumnName="m_attachment_id")
	public Attachment getThreeB2() {
		return threeB2;
	}
	public void setThreeB2(Attachment threeB2) {
		this.threeB2 = threeB2;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="three_b_3",referencedColumnName="m_attachment_id")
	public Attachment getThreeB3() {
		return threeB3;
	}
	public void setThreeB3(Attachment threeB3) {
		this.threeB3 = threeB3;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cancelled_cheque",referencedColumnName="m_attachment_id")
	public Attachment getCancelledCheque() {
		return cancelledCheque;
	}
	public void setCancelledCheque(Attachment cancelledCheque) {
		this.cancelledCheque = cancelledCheque;
	}
	@Column(name="is_msme")
	public String getIsMSME() {
		return isMSME;
	}
	public void setIsMSME(String isMSME) {
		this.isMSME = isMSME;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="msem_certificate",referencedColumnName="m_attachment_id")
	public Attachment getMsmeCertificate() {
		return msmeCertificate;
	}
	public void setMsmeCertificate(Attachment msmeCertificate) {
		this.msmeCertificate = msmeCertificate;
	}
	@Column(name="is_lower_deduction")
	public String getIsLowerDeduction() {
		return isLowerDeduction;
	}
	public void setIsLowerDeduction(String isLowerDeduction) {
		this.isLowerDeduction = isLowerDeduction;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lower_deduction_cert",referencedColumnName="m_attachment_id")
	public Attachment getLowerDeductionCert() {
		return lowerDeductionCert;
	}
	public void setLowerDeductionCert(Attachment lowerDeductionCert) {
		this.lowerDeductionCert = lowerDeductionCert;
	}
	@Column(name="valid_from")
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	@Column(name="valid_to")
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	@Column(name="ld_value")
	public String getLdValue() {
		return ldValue;
	}
	public void setLdValue(String ldValue) {
		this.ldValue = ldValue;
	}
	@Column(name="is_related_party")
	public String getIsRelatedParty() {
		return isRelatedParty;
	}
	public void setIsRelatedParty(String isRelatedParty) {
		this.isRelatedParty = isRelatedParty;
	}
	
	@Column(name="msme_number")
	public String getMsmeNumber() {
		return msmeNumber;
	}

	public void setMsmeNumber(String msmeNumber) {
		this.msmeNumber = msmeNumber;
	}

	
	@Column(name="msme_type")
	public String getMsmeType() {
		return msmeType;
	}
	public void setMsmeType(String msmeType) {
		this.msmeType = msmeType;
	}
	
	/*@OneToMany(fetch=FetchType.LAZY,mappedBy="kycDetails")
	public List<OtherDocuments> getOtherDocuments() {
		return otherDocuments;
	}
	public void setOtherDocuments(List<OtherDocuments> otherDocuments) {
		this.otherDocuments = otherDocuments;
	}*/
}
