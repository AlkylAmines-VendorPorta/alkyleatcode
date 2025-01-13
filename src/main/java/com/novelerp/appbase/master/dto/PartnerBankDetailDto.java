package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
/**
 * 
 * @author Vivek Birdi
 *
 */
public class PartnerBankDetailDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partnerBankDetailId;
	private BankNameDetailsDto bankNameDetails;
	private String accountNumber;
	private String ifscCode;
	private String benificaryName;
	private String mobileNo;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String isApproved;
	private String remark;
	private BankBranchDetailsDto branchName;
	private AttachmentDto bankDetailFile;
	
	
	public Long getPartnerBankDetailId() {
		return partnerBankDetailId;
	}
	public void setPartnerBankDetailId(Long partnerBankDetailId) {
		this.partnerBankDetailId = partnerBankDetailId;
	}
	/*public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}*/
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBenificaryName() {
		return benificaryName;
	}
	public void setBenificaryName(String benificaryName) {
		this.benificaryName = benificaryName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public AttachmentDto getBankDetailFile() {
		return bankDetailFile;
	}
	
	public void setBankDetailFile(AttachmentDto bankDetailFile) {
		this.bankDetailFile = bankDetailFile;
	}
	public BankNameDetailsDto getBankNameDetails() {
		return bankNameDetails;
	}
	public void setBankNameDetails(BankNameDetailsDto bankNameDetails) {
		this.bankNameDetails = bankNameDetails;
	}
	public BankBranchDetailsDto getBranchName() {
		return branchName;
	}
	public void setBranchName(BankBranchDetailsDto branchName) {
		this.branchName = branchName;
	}
	

}
