package com.novelerp.appbase.master.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
/**
 * 
 * @author Aman
 */
@Entity
@Table(name="m_bp_org_licence")
public class PartnerOrgLicense extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long partnerOrgLicenceId;
	private String licenceNo;
	private Date licenceValidityDate;
	private Attachment licenceCopy;
	private Attachment machinaryListCopy;
	private PartnerOrg partnerOrg;
	private String licenseType;
	
	@Id	
	@SequenceGenerator(name="m_bp_org_licence_Seq",sequenceName="m_bp_org_licence_Seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_licence_Seq")
	@Column(name="m_bp_org_licence_id" , updatable = false)
	public Long getPartnerOrgLicenceId() {
		return partnerOrgLicenceId;
	}
	public void setPartnerOrgLicenceId(Long partnerOrgLicenceId) {
		this.partnerOrgLicenceId = partnerOrgLicenceId;
	}
	@Column(name="licence_no")
	public String getLicenceNo() {
		return licenceNo;
	}
	
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	@Column(name="valid_from")
	public Date getLicenceValidityDate() {
		return licenceValidityDate;
	}
	public void setLicenceValidityDate(Date licenceValidityDate) {
		this.licenceValidityDate = licenceValidityDate;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="licencecopy",referencedColumnName="m_attachment_id")
	public Attachment getLicenceCopy() {
		return licenceCopy;
	}
	public void setLicenceCopy(Attachment licenceCopy) {
		this.licenceCopy = licenceCopy;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="machinarycopy",referencedColumnName="m_attachment_id")
	public Attachment getMachinaryListCopy() {
		return machinaryListCopy;
	}
	public void setMachinaryListCopy(Attachment machinaryListCopy) {
		this.machinaryListCopy = machinaryListCopy;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrg partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	@Column(name="license_type")
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	
}
