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
import com.novelerp.appcontext.entity.Location;
import com.novelerp.appcontext.entity.UserDetails;
/**
 * 
 * @author Aman
 */
@Entity
@Table(name="m_bp_signature")
public class PartnerSignatory extends ContextPO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partnerSignatoryId;
	private UserDetails userDetail;
	private Location location;
	private Date validFrom;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	private String holderType;
	private Attachment attorneyCertificate;
	private Attachment digitallySignTestDoc;
	private String otherDesignation;
	
	@Id
	@SequenceGenerator(name="m_bp_signature_seq",sequenceName="m_bp_signature_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_signature_seq")	
	@Column(name = "m_bp_signature_id", updatable=false)
	public Long getPartnerSignatoryId() {
		return partnerSignatoryId;
	}
	public void setPartnerSignatoryId(Long partnerSignatoryId) {
		this.partnerSignatoryId = partnerSignatoryId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_user_details_id")
	public UserDetails getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetails userDetail) {
		this.userDetail = userDetail;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_location_id")
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@Column(name="validfrom")
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
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
	@Column(name="holder_type")
	public String getHolderType() {
		return holderType;
	}
	public void setHolderType(String holderType) {
		this.holderType = holderType;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="power_of_attorney_certificate",referencedColumnName="m_attachment_id")
	public Attachment getAttorneyCertificate() {
		if(attorneyCertificate==null || attorneyCertificate.getAttachmentId()==null)
		{
			return null;
		}
		return attorneyCertificate;
	}
	public void setAttorneyCertificate(Attachment attorneyCertificate) {
		this.attorneyCertificate = attorneyCertificate;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="digitally_sign_test_doc",referencedColumnName="m_attachment_id")
	public Attachment getDigitallySignTestDoc() {
		if(digitallySignTestDoc==null || digitallySignTestDoc.getAttachmentId()==null)
		{
			return null;
		}
		return digitallySignTestDoc;
	}
	public void setDigitallySignTestDoc(Attachment digitallySignTestDoc) {
		this.digitallySignTestDoc = digitallySignTestDoc;
	}
	@Column(name="other_designation")
	public String getOtherDesignation() {
		return otherDesignation;
	}
	public void setOtherDesignation(String otherDesignation) {
		this.otherDesignation = otherDesignation;
	}
	
	
}

