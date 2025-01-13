package com.novelerp.alkyl.entity;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.core.util.DateUtil;
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name="t_asn_header")
public class SecurityPOHeader extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	
	private Long asnHeaderId;
//	private String poNumber;
//	private Integer asnNumber;
//	private Date asnDate;
	private Integer advanceShipmentNoticeNo;
	private String status; 
	private String transporterNo;
	private String lrNumber;
	private Date lrDate;
	private String vehicalNo;
	private String grossWeight;
	private String tareWeight;
	private String numberOfPackages;
	private String netWeight;
//	private UserDto User;
	private Date invoiceDate;
	private Date postingDate;
	private String invoiceNo;
	private PurchaseOrder po;
	private User user;
	private String nameOfDriver;	
	private String mobileNumber;
	private Date reportedDate;
	private String photoIdProof;	
	private Attachment photoIdProofAtt;
	private User reportedBy;
	private Date gateinDate;
	private User gateinBy;
	
	@Id
	@SequenceGenerator(name = "t_asn_header_seq", sequenceName = "t_asn_header_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_asn_header_seq")
	@Column(name="t_asn_header_id",updatable = false)
	public Long getAsnHeaderId() {
		return asnHeaderId;
	}
	public void setAsnHeaderId(Long asnHeaderId) {
		this.asnHeaderId = asnHeaderId;
	}
//	@Column(name="purchase_order_number")
//	public String getPoNumber() {
//		return poNumber;
//	}
//	
//	public void setPoNumber(String poNumber) {
//		this.poNumber = poNumber;
//	}
	
	@Column(name="advance_shipment_notice_number")
	public Integer getAdvanceShipmentNoticeNo() {
		return advanceShipmentNoticeNo;
	}
	public void setAdvanceShipmentNoticeNo(Integer advanceShipmentNoticeNo) {
		this.advanceShipmentNoticeNo = advanceShipmentNoticeNo;
	}
	
//	public Integer getAsnNumber() {
//		return asnNumber;
//	}
//	
//	public void setAsnNumber(Integer asnNumber) {
//		this.asnNumber = asnNumber;
//	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Column(name="transporter_no")
	public String getTransporterNo() {
		return transporterNo;
	}
	public void setTransporterNo(String transporterNo) {
		this.transporterNo = transporterNo;
	}
	
	@Column(name="lr_number")
	public String getLrNumber() {
		return lrNumber;
	}


	public void setLrNumber(String lrNumber) {
		this.lrNumber = lrNumber;
	}
	
	@Column(name="lr_date")
	public Date getLrDate() {
		return lrDate;
	}
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}
	
	@Column(name="vehical_no")
	public String getVehicalNo() {
		return vehicalNo;
	}
	public void setVehicalNo(String vehicalNo) {
		this.vehicalNo = vehicalNo;
	}
	
	@Column(name="gross_weight")
	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	
	@Column(name="tare_weight")
	public String getTareWeight() {
		return tareWeight;
	}
	public void setTareWeight(String tareWeight) {
		this.tareWeight = tareWeight;
	}
	
	@Column(name="number_of_packages")
	public String getNumberOfPackages() {
		return numberOfPackages;
	}
	public void setNumberOfPackages(String numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}
	
	@Column(name="net_weight")
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="ad_user_id", referencedColumnName="ad_user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Column(name="invoice_date")
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	@Column(name="posting_date")
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	
	@Column(name="invoice_no")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_purchase_order_id",referencedColumnName="t_purchase_order_id")
	public PurchaseOrder getPo() {
		return po;
	}
	public void setPo(PurchaseOrder po) {
		this.po = po;
	}
	
	@Column(name="name_of_driver")
	public String getNameOfDriver() {
		return nameOfDriver;
	}
	public void setNameOfDriver(String nameOfDriver) {
		this.nameOfDriver = nameOfDriver;
	}
	
	@Column(name="mobile_no")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
	@Column(name="reported_date")
	public Date getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	
	@Column(name="id_proof")
	public String getPhotoIdProof() {
		return photoIdProof;
	}
	public void setPhotoIdProof(String photoIdProof) {
		this.photoIdProof = photoIdProof;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proof_attachment", referencedColumnName="m_attachment_id")
	public Attachment getPhotoIdProofAtt() {
		return photoIdProofAtt;
	}
	public void setPhotoIdProofAtt(Attachment photoIdProofAtt) {
		this.photoIdProofAtt = photoIdProofAtt;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reportedby",referencedColumnName="ad_user_id")
	public User getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(User reportedBy) {
		this.reportedBy = reportedBy;
	}
	
	@Column(name="gatein_date")
	public Date getGateinDate() {
		return gateinDate;
	}
	public void setGateinDate(Date gateinDate) {
		this.gateinDate = gateinDate;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gatein_by",referencedColumnName="ad_user_id")
	public User getGateinBy() {
		return gateinBy;
	}
	public void setGateinBy(User gateinBy) {
		this.gateinBy = gateinBy;
	}
	
	
}
