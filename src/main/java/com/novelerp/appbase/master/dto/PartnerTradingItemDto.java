package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
/**
 * 
 * @author Aman
 */
public class PartnerTradingItemDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long partnerTradingItemId;	
	private String name;			
	private PartnerItemManufacturerDto partnerItemManufacutrer;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	private MaterialDto material;
	
	public Long getPartnerTradingItemId() {
		return partnerTradingItemId;
	}
	public void setPartnerTradingItemId(Long partnerTradingItemId) {
		this.partnerTradingItemId = partnerTradingItemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PartnerItemManufacturerDto getPartnerItemManufacutrer() {
		return partnerItemManufacutrer;
	}
	public void setPartnerItemManufacutrer(PartnerItemManufacturerDto partnerItemManufacutrer) {
		this.partnerItemManufacutrer = partnerItemManufacutrer;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
	
		
}
