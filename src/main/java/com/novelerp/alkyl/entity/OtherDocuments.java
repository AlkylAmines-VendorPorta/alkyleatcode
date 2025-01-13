package com.novelerp.alkyl.entity;

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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_bp_other_documents")
public class OtherDocuments extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long otherDocumentId;
	private Attachment attachmentdetails;
	private KYC kycDetails;
	private String name;
	private String code;
	private String description;
	
	
	@Id
	@SequenceGenerator(name = "m_bp_other_documents_seq", sequenceName = "m_bp_other_documents_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_bp_other_documents_seq")
	@Column(name="m_bp_other_documents_id", updatable=false)
	public Long getOtherDocumentId() {
		return otherDocumentId;
	}
	public void setOtherDocumentId(Long otherDocumentId) {
		this.otherDocumentId = otherDocumentId;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id",referencedColumnName="m_attachment_id")
	public Attachment getAttachmentdetails() {
		return attachmentdetails;
	}
	public void setAttachmentdetails(Attachment attachmentdetails) {
		this.attachmentdetails = attachmentdetails;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_kyc_id",referencedColumnName="m_bp_kyc_id")
	public KYC getKycDetails() {
		return kycDetails;
	}
	public void setKycDetails(KYC kycDetails) {
		this.kycDetails = kycDetails;
	}
	
}
