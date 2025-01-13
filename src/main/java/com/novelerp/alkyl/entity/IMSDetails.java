package com.novelerp.alkyl.entity;

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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_bp_ims_details")
public class IMSDetails extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long imsId;
	private String isRawMaterial;
    private String isInProcess;
    private String isFinishedProduct;
    private String inspectionStandard;
    private String majorTestingInstru;
    private String isFCRM;
    private String isISIApproved;
    private String isISOCertified;
    private String isISO14001;
    private Date iso14001ValidUpto;
    private Attachment iso14001Attachment;
    private String isISO9001;
    private Date iso9001ValidUpto;
    private Attachment iso9001Attachment;
    private String isISO50001;
    private Date iso50001ValidUpto;
    private Attachment iso50001Attachment;
    private String isRCLogo ;
    private Date isRCLogoValidUpto;
    private Attachment isRCLogoAttachment;
    private String isOHSMS45001;
    private Date ohsms45001ValidUpto;
    private Attachment  ohsms45001Attachment;
    
    @Id
	@SequenceGenerator(name = "m_bp_ims_details_seq", sequenceName = "m_bp_ims_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_bp_ims_details_seq")
	@Column(name = "m_bp_ims_details_id", updatable = false)
	public Long getImsId() {
		return imsId;
	}
	public void setImsId(Long imsId) {
		this.imsId = imsId;
	}
	@Column(name="is_raw_material")
	public String getIsRawMaterial() {
		return isRawMaterial;
	}
	public void setIsRawMaterial(String isRawMaterial) {
		this.isRawMaterial = isRawMaterial;
	}
	@Column(name="is_in_process")
	public String getIsInProcess() {
		return isInProcess;
	}
	public void setIsInProcess(String isInProcess) {
		this.isInProcess = isInProcess;
	}
	@Column(name="is_finished_product")
	public String getIsFinishedProduct() {
		return isFinishedProduct;
	}
	public void setIsFinishedProduct(String isFinishedProduct) {
		this.isFinishedProduct = isFinishedProduct;
	}
	@Column(name="inspection_std")
	public String getInspectionStandard() {
		return inspectionStandard;
	}
	public void setInspectionStandard(String inspectionStandard) {
		this.inspectionStandard = inspectionStandard;
	}
	@Column(name="major_testing_instru")
	public String getMajorTestingInstru() {
		return majorTestingInstru;
	}
	public void setMajorTestingInstru(String majorTestingInstru) {
		this.majorTestingInstru = majorTestingInstru;
	}
	@Column(name="is_fcrm")
	public String getIsFCRM() {
		return isFCRM;
	}
	public void setIsFCRM(String isFCRM) {
		this.isFCRM = isFCRM;
	}
	@Column(name="is_isi_approved")
	public String getIsISIApproved() {
		return isISIApproved;
	}
	public void setIsISIApproved(String isISIApproved) {
		this.isISIApproved = isISIApproved;
	}
	@Column(name="is_iso_certified")
	public String getIsISOCertified() {
		return isISOCertified;
	}
	public void setIsISOCertified(String isISOCertified) {
		this.isISOCertified = isISOCertified;
	}
	@Column(name="is_iso_14001")
	public String getIsISO14001() {
		return isISO14001;
	}
	public void setIsISO14001(String isISO14001) {
		this.isISO14001 = isISO14001;
	}
	@Column(name="is_iso_14001_valid_to")
	public Date getIso14001ValidUpto() {
		return iso14001ValidUpto;
	}
	public void setIso14001ValidUpto(Date iso14001ValidUpto) {
		this.iso14001ValidUpto = iso14001ValidUpto;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="is_iso_14001_att",referencedColumnName="m_attachment_id")
	public Attachment getIso14001Attachment() {
		return iso14001Attachment;
	}
	public void setIso14001Attachment(Attachment iso14001Attachment) {
		this.iso14001Attachment = iso14001Attachment;
	}
	@Column(name="is_iso_9001")
	public String getIsISO9001() {
		return isISO9001;
	}
	public void setIsISO9001(String isISO9001) {
		this.isISO9001 = isISO9001;
	}
	@Column(name="is_iso_9001_valid_to")
	public Date getIso9001ValidUpto() {
		return iso9001ValidUpto;
	}
	public void setIso9001ValidUpto(Date iso9001ValidUpto) {
		this.iso9001ValidUpto = iso9001ValidUpto;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="is_iso_9001_att",referencedColumnName="m_attachment_id")
	public Attachment getIso9001Attachment() {
		return iso9001Attachment;
	}
	public void setIso9001Attachment(Attachment iso9001Attachment) {
		this.iso9001Attachment = iso9001Attachment;
	}
	@Column(name="is_iso_50001")
	public String getIsISO50001() {
		return isISO50001;
	}
	public void setIsISO50001(String isISO50001) {
		this.isISO50001 = isISO50001;
	}
	@Column(name="is_iso_50001_valid_to")
	public Date getIso50001ValidUpto() {
		return iso50001ValidUpto;
	}
	public void setIso50001ValidUpto(Date iso50001ValidUpto) {
		this.iso50001ValidUpto = iso50001ValidUpto;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="is_iso_50001_att",referencedColumnName="m_attachment_id")
	public Attachment getIso50001Attachment() {
		return iso50001Attachment;
	}
	public void setIso50001Attachment(Attachment iso50001Attachment) {
		this.iso50001Attachment = iso50001Attachment;
	}
	@Column(name="is_rc_logo")
	public String getIsRCLogo() {
		return isRCLogo;
	}
	public void setIsRCLogo(String isRCLogo) {
		this.isRCLogo = isRCLogo;
	}
	@Column(name="is_rc_logo_valid_to")
	public Date getIsRCLogoValidUpto() {
		return isRCLogoValidUpto;
	}
	public void setIsRCLogoValidUpto(Date isRCLogoValidUpto) {
		this.isRCLogoValidUpto = isRCLogoValidUpto;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="is_rc_logo_att",referencedColumnName="m_attachment_id")
	public Attachment getIsRCLogoAttachment() {
		return isRCLogoAttachment;
	}
	public void setIsRCLogoAttachment(Attachment isRCLogoAttachment) {
		this.isRCLogoAttachment = isRCLogoAttachment;
	}
	@Column(name="is_iso_45001")
	public String getIsOHSMS45001() {
		return isOHSMS45001;
	}
	public void setIsOHSMS45001(String isOHSMS45001) {
		this.isOHSMS45001 = isOHSMS45001;
	}
	@Column(name="is_iso_45001_valid_to")
	public Date getOhsms45001ValidUpto() {
		return ohsms45001ValidUpto;
	}
	public void setOhsms45001ValidUpto(Date ohsms45001ValidUpto) {
		this.ohsms45001ValidUpto = ohsms45001ValidUpto;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="is_iso_45001_att",referencedColumnName="m_attachment_id")
	public Attachment getOhsms45001Attachment() {
		return ohsms45001Attachment;
	}
	public void setOhsms45001Attachment(Attachment ohsms45001Attachment) {
		this.ohsms45001Attachment = ohsms45001Attachment;
	}
}
