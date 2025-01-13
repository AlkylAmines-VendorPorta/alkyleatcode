package com.novelerp.appbase.master.entity;

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

import com.novelerp.appcontext.entity.Bpartner;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name = "m_bpartner_invitation")
public class MBPartnerInvitation extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5114652250037675343L;
	
	private long mBPartnerInvitationId;
	private String companyName;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String vendorEmailId;
	private String vendorPancardNo;
	private String isInvitationSend;
	private String isInvitationApproved;
	private Bpartner requestedPartner;
	
	@Id
	@SequenceGenerator(name = "m_bpartner_invitation_seq", sequenceName = "m_bpartner_invitation_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_bpartner_invitation_seq")
	@Column(name = "m_bpartner_invitation_id", updatable = false)
	public long getmBPartnerInvitationId() {
		return mBPartnerInvitationId;
	}
	public void setmBPartnerInvitationId(long mBPartnerInvitationId) {
		this.mBPartnerInvitationId = mBPartnerInvitationId;
	}
	
	

	@Column(name="vendor_email_id")
	public String getVendorEmailId() {
		return vendorEmailId;
	}
	public void setVendorEmailId(String vendorEmailId) {
		this.vendorEmailId = vendorEmailId;
	}
	
	@Column(name="vendor_pancardno")
	public String getVendorPancardNo() {
		return vendorPancardNo;
	}
	public void setVendorPancardNo(String vendorPancardNo) {
		this.vendorPancardNo = vendorPancardNo;
	}
	
	
	@Column(name="is_invitation_send")
	public String getIsInvitationSend() {
		return isInvitationSend;
	}
	public void setIsInvitationSend(String isInvitationSend) {
		this.isInvitationSend = isInvitationSend;
	}
	
	@Column(name="is_invitation_approved")
	public String getIsInvitationApproved() {
		return isInvitationApproved;
	}
	public void setIsInvitationApproved(String isInvitationApproved) {
		this.isInvitationApproved = isInvitationApproved;
	}
	@Column(name="company_name")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="requested_bpartner")
	public Bpartner getRequestedPartner() {
		return requestedPartner;
	}
	public void setRequestedPartner(Bpartner requestedPartner) {
		this.requestedPartner = requestedPartner;
	}

	
	
	
	

}
