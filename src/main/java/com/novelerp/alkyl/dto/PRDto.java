package com.novelerp.alkyl.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.util.DateUtil;

public class PRDto extends CommonContextDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long prId;
	private String code;
	private String name;
	private String description;
	private String prNumber;
	private UserDto requestedBy;
	private UserDto tcApprover;
	private String status;
	private String docType;
	private String isTC;
	private List<PRLineDto> prLines;
	private String pstyp;
	private UserDto buyer;
	private String priority;
	private String assignment;
	private UserDto approvedBy;
	private List<PRAttachmentDto> prAttSet;
	private List<ThirdPartyPRApproverDto> emailSet;
	private Date date;
	private String remarks;
	private String qcfNo;
	private Date bidEndDate;
	private String headerText;
	private UserDto createdBy;
	private UserDto releasedBy;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date releasedDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date approvedDate;
	private UserDto pmapprovedBy;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date pmapprovedDate;
	
	
	
	public Long getPrId() {
		return prId;
	}
	public void setPrId(Long prId) {
		this.prId = prId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public UserDto getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(UserDto requestedBy) {
		this.requestedBy = requestedBy;
	}
	public UserDto getTcApprover() {
		return tcApprover;
	}
	public void setTcApprover(UserDto tcApprover) {
		this.tcApprover = tcApprover;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getIsTC() {
		if(null==isTC){
			return "N";
		}
		return isTC;
	}
	public void setIsTC(String isTC) {
		this.isTC = isTC;
	}
	public List<PRLineDto> getPrLines() {
		return prLines;
	}
	public void setPrLines(List<PRLineDto> prLines) {
		this.prLines = prLines;
	}
	public String getPrNumber() {
		return prNumber;
	}
	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
	}
	
	public String getPstyp() {
		return pstyp;
	}
	public void setPstyp(String pstyp) {
		this.pstyp = pstyp;
	}
	public UserDto getBuyer() {
		return buyer;
	}
	public void setBuyer(UserDto buyer) {
		this.buyer = buyer;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public UserDto getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(UserDto approvedBy) {
		this.approvedBy = approvedBy;
	}
	public List<PRAttachmentDto> getPrAttSet() {
		return prAttSet;
	}
	public void setPrAttSet(List<PRAttachmentDto> prAttSet) {
		this.prAttSet = prAttSet;
	}
	public List<ThirdPartyPRApproverDto> getEmailSet() {
		return emailSet;
	}
	public void setEmailSet(List<ThirdPartyPRApproverDto> emailSet) {
		this.emailSet = emailSet;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getQcfNo() {
		return qcfNo;
	}
	public void setQcfNo(String qcfNo) {
		this.qcfNo = qcfNo;
	}
	public Date getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	public UserDto getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}
	public UserDto getReleasedBy() {
		return releasedBy;
	}
	public void setReleasedBy(UserDto releasedBy) {
		this.releasedBy = releasedBy;
	}
	public Date getReleasedDate() {
		return releasedDate;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public UserDto getPmapprovedBy() {
		return pmapprovedBy;
	}
	public Date getPmapprovedDate() {
		return pmapprovedDate;
	}
	public void setPmapprovedBy(UserDto pmapprovedBy) {
		this.pmapprovedBy = pmapprovedBy;
	}
	public void setPmapprovedDate(Date pmapprovedDate) {
		this.pmapprovedDate = pmapprovedDate;
	}

	
	

	
	
}
