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
 * @author Aman
 */
@Entity
@Table(name="m_bp_org_trading_item")
public class PartnerTradingItem extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerTradingItemId;	
	private String name;			
	private PartnerItemManufacturer partnerItemManufacutrer;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	private Material material;
	
	@Id
	@SequenceGenerator(name="m_bp_org_trading_item_seq",sequenceName="m_bp_org_trading_item_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_trading_item_seq")	
	@Column(name = "m_bp_org_trading_item_id", updatable=false)
	public Long getPartnerTradingItemId() {
		return partnerTradingItemId;
	}
	public void setPartnerTradingItemId(Long partnerTradingItemId) {
		this.partnerTradingItemId = partnerTradingItemId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_item_manufacturer_id")
	public PartnerItemManufacturer getPartnerItemManufacutrer() {
		return partnerItemManufacutrer;
	}
	public void setPartnerItemManufacutrer(PartnerItemManufacturer partnerItemManufacutrer) {
		this.partnerItemManufacutrer = partnerItemManufacutrer;
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
	@JoinColumn(name="m_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
	
}


