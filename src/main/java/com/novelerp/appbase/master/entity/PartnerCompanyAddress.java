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
import com.novelerp.appcontext.entity.Location;
import com.novelerp.appcontext.entity.User;

/**
 * 
 * @author Aman
 *
 */
@Entity
@Table(name="m_bp_company_address")
public class PartnerCompanyAddress extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long partnerCompanyAddressId;
	private Location location;
	private String isShipToAddress;
	private String isBillToAddress;
	private String locationType;
	private PartnerCompanyAddress officeLocation;
	private String remark;
	private String isApproved;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String nameOfCompany;
	private String isPrimaryAccount;
	

	
	@Id
	@SequenceGenerator(name="m_bp_company_address_seq",sequenceName="m_bp_company_address_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_company_address_seq")	
	@Column(name = "m_bp_company_address_id", updatable=false)
	public Long getPartnerCompanyAddressId() {
		return partnerCompanyAddressId;
	}
	public void setPartnerCompanyAddressId(Long partnerCompanyAddressId) {
		this.partnerCompanyAddressId = partnerCompanyAddressId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_location_id")
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	@Column(name="isshipto")
	public String getIsShipToAddress() {
		return isShipToAddress;
	}
	public void setIsShipToAddress(String isShipToAddress) {
		this.isShipToAddress = isShipToAddress;
	}
	@Column(name="isbillto")
	public String getIsBillToAddress() {
		return isBillToAddress;
	}
	public void setIsBillToAddress(String isBillToAddress) {
		this.isBillToAddress = isBillToAddress;
	}
	@Column(name="locationType")
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="office_location_id", referencedColumnName="m_bp_company_address_id")
	public PartnerCompanyAddress getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(PartnerCompanyAddress officeLocation) {
		this.officeLocation = officeLocation;
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
	
	@Column(name="name_of_company")
	public String getNameOfCompany() {
		return nameOfCompany;
	}
	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}
	@Column(name="is_primary_account")
	public String getIsPrimaryAccount() {
		return isPrimaryAccount;
	}
	public void setIsPrimaryAccount(String isPrimaryAccount) {
		this.isPrimaryAccount = isPrimaryAccount;
	}

	

	
	
}
