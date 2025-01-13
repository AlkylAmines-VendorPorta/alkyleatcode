package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Entity
@Table(name="m_bp_infra_item")
public class PartnerInfraItem extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partnerInfraItemId;
	private Material material;
	private Attachment attachment;
	private String status;
	private Long levelNo;
		
	@Id
	@SequenceGenerator(name="m_bp_infra_item_seq",sequenceName="m_bp_infra_item_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_infra_item_seq")	
	@Column(name = "m_bp_infra_item_id", updatable=false)
	public Long getPartnerInfraItemId() {
		return partnerInfraItemId;
	}
	public void setPartnerInfraItemId(Long partnerInfraItemId) {
		this.partnerInfraItemId = partnerInfraItemId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="level_no")
	public Long getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(Long levelNo) {
		this.levelNo = levelNo;
	}
	
	

}
