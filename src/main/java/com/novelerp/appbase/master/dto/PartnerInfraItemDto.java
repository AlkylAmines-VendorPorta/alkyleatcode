package com.novelerp.appbase.master.dto;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Varsha Patil
 *
 */
public class PartnerInfraItemDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partnerInfraItemId;
	private MaterialDto material;
	private Attachment attachment;
	private String status;
	private Long levelNo;
	public Long getPartnerInfraItemId() {
		return partnerInfraItemId;
	}
	public void setPartnerInfraItemId(Long partnerInfraItemId) {
		this.partnerInfraItemId = partnerInfraItemId;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(Long levelNo) {
		this.levelNo = levelNo;
	}
	
}
