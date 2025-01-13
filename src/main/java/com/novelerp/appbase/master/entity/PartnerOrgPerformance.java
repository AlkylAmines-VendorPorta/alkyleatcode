package com.novelerp.appbase.master.entity;

import java.util.Date;

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
@Table(name="m_bp_org_performance")
public class PartnerOrgPerformance extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgPerformanceId;
	private Material material;
	private String firmName;
	private Date orderStartDate;
	private Date orderEndDate;
	private String quantitySupplied;
	private String referenc1;
	private String referenc2;
	
	private Attachment certificateAward;
	private PartnerOrg partnerOrg;
	private String isApproved;
	private String remark;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String poNumber;
	
	@Id
	@SequenceGenerator(name="m_bp_org_performance_seq",sequenceName="m_bp_org_performance_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_performance_seq")	
	@Column(name = "m_bp_org_performance_id", updatable=false)
	public Long getPartnerOrgPerformanceId() {
		return partnerOrgPerformanceId;
	}
	public void setPartnerOrgPerformanceId(Long partnerOrgPerformanceId) {
		this.partnerOrgPerformanceId = partnerOrgPerformanceId;
	}
	@Column(name = "order_startdate")
	public Date getOrderStartDate() {
		return orderStartDate;
	}
	
	public void setOrderStartDate(Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}
	@Column(name = "order_enddate")
	public Date getOrderEndDate() {
		return orderEndDate;
	}
	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	@Column(name = "quantity_supplied")
	public String getQuantitySupplied() {
		return quantitySupplied;
	}
	public void setQuantitySupplied(String quantitySupplied) {
		this.quantitySupplied = quantitySupplied;
	}
	@Column(name = "reference_1")
	public String getReferenc1() {
		return referenc1;
	}
	public void setReferenc1(String referenc1) {
		this.referenc1 = referenc1;
	}
	@Column(name = "referenc_2")
	public String getReferenc2() {
		return referenc2;
	}
	public void setReferenc2(String referenc2) {
		this.referenc2 = referenc2;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getCertificateAward() {
		if(certificateAward==null || certificateAward.getAttachmentId()==null)
		{
			return null;
		}
		return certificateAward;
	}
	public void setCertificateAward(Attachment certificateAward) {
		this.certificateAward = certificateAward;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	@Column(name = "firm_name")
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrg partnerOrg) {
		this.partnerOrg = partnerOrg;
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
	@Column(name="po_number")
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
}
