package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Entity
@Table(name="m_bp_bank_detail")
public class PartnerBankDetail extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long partnerBankDetailId;
	private BankNameDetails bankNameDetails;
	private BankBranchDetails branchName;
	private String accountNumber;
	private String ifscCode;
	private String benificaryName;
	private String mobileNo;
	private String isApproved;
	private String remark;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private Attachment bankDetailFile;
	
	
	@Id
	@SequenceGenerator(name="m_bp_bank_detail_seq",sequenceName="m_bp_bank_detail_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_bank_detail_seq")	
	@Column(name = "m_bp_bank_detail_id", updatable=false)
	public Long getPartnerBankDetailId() {
		return partnerBankDetailId;
	}
	public void setPartnerBankDetailId(Long partnerBankDetailId) {
		this.partnerBankDetailId = partnerBankDetailId;
	}
	
	@Column(name="account_number")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Column(name="ifsc_code")
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	@Column(name="benificiary_name")
	public String getBenificaryName() {
		return benificaryName;
	}
	public void setBenificaryName(String benificaryName) {
		this.benificaryName = benificaryName;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="is_ee_approved")
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	@Column(name="ee_comment")
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	@Column(name="is_ce_approved")
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	@Column(name="ce_comment")
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	@Column(name="is_approved")
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bank_detail_file",referencedColumnName="m_attachment_id")
	public Attachment getBankDetailFile() {
		if(bankDetailFile==null || bankDetailFile.getAttachmentId()==null)
		{
			return null;
		}
		return bankDetailFile;
	}
	
	
	public void setBankDetailFile(Attachment bankDetailFile) {
		this.bankDetailFile = bankDetailFile;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bank_name_details_id")
	public BankNameDetails getBankNameDetails() {
		return bankNameDetails;
	}
	public void setBankNameDetails(BankNameDetails bankNameDetails) {
		this.bankNameDetails = bankNameDetails;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bank_branch_details_id")
	public BankBranchDetails getBranchName() {
		return branchName;
	}
	public void setBranchName(BankBranchDetails branchName) {
		this.branchName = branchName;
	}
	
	
	
}
