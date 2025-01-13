package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/***
 * 
 * @author Varsha Patil
 *
 */
public class PartnerItemDrawingDocDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partnerItemDrawingDocId;
    private MaterialDrawingDocumentDto materialDrawingDoc;
    private PartnerInfraItemDto partnerInfraItem;
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
	public Long getPartnerItemDrawingDocId() {
		return partnerItemDrawingDocId;
	}
	public void setPartnerItemDrawingDocId(Long partnerItemDrawingDocId) {
		this.partnerItemDrawingDocId = partnerItemDrawingDocId;
	}
	public MaterialDrawingDocumentDto getMaterialDrawingDoc() {
		return materialDrawingDoc;
	}
	public void setMaterialDrawingDoc(MaterialDrawingDocumentDto materialDrawingDoc) {
		this.materialDrawingDoc = materialDrawingDoc;
	}
	public PartnerInfraItemDto getPartnerInfraItem() {
		return partnerInfraItem;
	}
	public void setPartnerInfraItem(PartnerInfraItemDto partnerInfraItem) {
		this.partnerInfraItem = partnerInfraItem;
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
