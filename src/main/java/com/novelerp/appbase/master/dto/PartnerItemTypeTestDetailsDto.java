package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;
/**
 * 
 * @author Varsha Patil
 *
 */
public class PartnerItemTypeTestDetailsDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partnerItemTypeTestId;
	private PartnerInfraItemDto partnerInfraItem;
	private MaterialTypeTestDetailsDto materialTypeTestDetails;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date issueDate;
	private String letterNo; 
	private String status;
	private String remark;
	private String isEEApproved;
	private String isSEApproved;
	private String isCEApproved;
	private String isEDApproved;
	private String isDIRApproved;
	private String eeComment;
	private String seComment;
	private String ceComment;
	private String edComment;
	private String dirComment;
	public Long getPartnerItemTypeTestId() {
		return partnerItemTypeTestId;
	}
	public void setPartnerItemTypeTestId(Long partnerItemTypeTestId) {
		this.partnerItemTypeTestId = partnerItemTypeTestId;
	}
	public PartnerInfraItemDto getPartnerInfraItem() {
		return partnerInfraItem;
	}
	public void setPartnerInfraItem(PartnerInfraItemDto partnerInfraItem) {
		this.partnerInfraItem = partnerInfraItem;
	}
	public MaterialTypeTestDetailsDto getMaterialTypeTestDetails() {
		return materialTypeTestDetails;
	}
	public void setMaterialTypeTestDetails(MaterialTypeTestDetailsDto materialTypeTestDetails) {
		this.materialTypeTestDetails = materialTypeTestDetails;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getLetterNo() {
		return letterNo;
	}
	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	public String getIsSEApproved() {
		return isSEApproved;
	}
	public void setIsSEApproved(String isSEApproved) {
		this.isSEApproved = isSEApproved;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getIsEDApproved() {
		return isEDApproved;
	}
	public void setIsEDApproved(String isEDApproved) {
		this.isEDApproved = isEDApproved;
	}
	public String getIsDIRApproved() {
		return isDIRApproved;
	}
	public void setIsDIRApproved(String isDIRApproved) {
		this.isDIRApproved = isDIRApproved;
	}
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	public String getSeComment() {
		return seComment;
	}
	public void setSeComment(String seComment) {
		this.seComment = seComment;
	}
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getEdComment() {
		return edComment;
	}
	public void setEdComment(String edComment) {
		this.edComment = edComment;
	}
	public String getDirComment() {
		return dirComment;
	}
	public void setDirComment(String dirComment) {
		this.dirComment = dirComment;
	}
		
}
