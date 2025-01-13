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
@Table(name="m_bp_org_product")
public class PartnerOrgProduct extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long partnerOrgProductId;
	private double qtyManufacturedPM;
	private double turnOver;
	private String licenceNo;
	private PartnerOrg partnerOrg;
	private Material material;
	private UOM uom;
	private String registrationType;
	private PartnerOrgRegistration partnerOrgRegistration;
	private PartnerOrgBIS partnerOrgBIS;
	private Attachment industrialLicenceCopy;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	
	@Id
	@SequenceGenerator(name="m_bp_org_product_seq",sequenceName="m_bp_org_product_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_product_seq")	
	@Column(name = "m_bp_org_product_id", updatable=false)
	public Long getPartnerOrgProductId() {
		return partnerOrgProductId;
	}
	public void setPartnerOrgProductId(Long partnerOrgProductId) {
		this.partnerOrgProductId = partnerOrgProductId;
	}
	@Column(name="qty_pm")
	public double getQtyManufacturedPM() {
		return qtyManufacturedPM;
	}
	public void setQtyManufacturedPM(double qtyManufacturedPM) {
		this.qtyManufacturedPM = qtyManufacturedPM;
	}
	@Column(name="turnover")
	public double getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(double turnOver) {
		this.turnOver = turnOver;
	}
	@Column(name="licenceno")
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrg partnerOrg) {
		this.partnerOrg = partnerOrg;
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
	@JoinColumn(name="m_uom_id")
	public UOM getUom() {
		return uom;
	}
	public void setUom(UOM uom) {
		this.uom = uom;
	}
	@Column(name="registration_type")
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_registration_id")
	public PartnerOrgRegistration getPartnerOrgRegistration() {
		if(partnerOrgRegistration==null || partnerOrgRegistration.getPartnerOrgRegistrationId()==null)
		{
			return null;
		}
		return partnerOrgRegistration;
	}
	public void setPartnerOrgRegistration(PartnerOrgRegistration partnerOrgRegistration) {
		this.partnerOrgRegistration = partnerOrgRegistration;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_bis_id")
	public PartnerOrgBIS getPartnerOrgBIS() {
		if(partnerOrgBIS==null || partnerOrgBIS.getPartnerOrgBisId()==null)
		{
			return null;
		}
		return partnerOrgBIS;
	}
	public void setPartnerOrgBIS(PartnerOrgBIS partnerOrgBIS) {
		this.partnerOrgBIS = partnerOrgBIS;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getIndustrialLicenceCopy() {
		if(industrialLicenceCopy==null || industrialLicenceCopy.getAttachmentId()==null)
		{
			return null;
		}
		return industrialLicenceCopy;
	}
	public void setIndustrialLicenceCopy(Attachment industrialLicenceCopy) {
		this.industrialLicenceCopy = industrialLicenceCopy;
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
	
}
