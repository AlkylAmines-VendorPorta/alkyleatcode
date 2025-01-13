package com.novelerp.appcontext.dto;

/**
 * 
 * @author varsha
 *
 */
public class BpartnerApprovalDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bpartnerApprovalId;
	private BPartnerDto approvalPartner;
	private String isASApproved;/*Account Section*/
	private String isPGApproved;/*Purchase Group*/
	private String isEEApproved;/*Executive Engineer*/
	private String isSEApproved;/*Superintend Engineer*/
	private String isCEApproved;/*Chief Engineer*/
	public Long getBpartnerApprovalId() {
		return bpartnerApprovalId;
	}
	public void setBpartnerApprovalId(Long bpartnerApprovalId) {
		this.bpartnerApprovalId = bpartnerApprovalId;
	}
	
	public BPartnerDto getApprovalPartner() {
		return approvalPartner;
	}
	public void setApprovalPartner(BPartnerDto approvalPartner) {
		this.approvalPartner = approvalPartner;
	}
	public String getIsASApproved() {
		return isASApproved;
	}
	public void setIsASApproved(String isASApproved) {
		this.isASApproved = isASApproved;
	}
	public String getIsPGApproved() {
		return isPGApproved;
	}
	public void setIsPGApproved(String isPGApproved) {
		this.isPGApproved = isPGApproved;
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
	
}
