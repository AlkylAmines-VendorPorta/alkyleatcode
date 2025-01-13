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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.util.DateUtil;

@Entity
@Table(name="t_advance_shipment_notice")
public class AdvanceShipmentNotice extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serviceSheetNo;
	private Long advanceShipmentNoticeId;
	private Integer advanceShipmentNoticeNo;
	private String invoiceNo;
	private Date invoiceDate;
	private Date deliveryNoteDate;
	private Date invoiceNoDate;
	private Date lrDate;
	private Double invoiceAmount;
	private Double mismatchAmount;
	private String deliveryNoteNo;
	private String lrNumber;
	private String transporterNo;
	private String vehicalNo;
	private String eWayBillNo;

	private String grossWeight;
	private String tareWeight;
	private Double numberOfPackages;
	private String isCOA;
	private String isPackingList;
	private String typeOfPackingBulk;
	private String remarks;
	private String name;
	private String code;
	private String description;
	private PurchaseOrder po;
	private Double igst;
	private Double sgst;
	private Double cgst;
	private Double tcs;
	private Double freightCharges;
	private Attachment invoice;

	private Double packingCharges;

	private Double basicAmount;
	
	private String status; 
	
	private String nameOfDriver;
	
	private String mobileNumber;
	
	private String photoIdProof;
	
	private Attachment photoIdProofAtt;
	
	private String irn;
	
	private Double loadingUnloadingCharges;
	
	private String invoiceApplicable;
	
	private String grnId;
	private String grnYear;
	private String rejectReason;
	private String issueDate;
	
	private String isGrn;
	private String isGateOut;
	private String isQCPassed;
	private String isUnload;
	private String isGateIn;
	private String isreported;
	private String isSafetyPassed;
	private String isOHCPassed;
	private Date grnDate;
	private Date gateOutDate;
	private Date qcPassedDate;
	private Date unloadDate;
	private Date gateInDate;
	private Date reportedDate;
	private Date safetyPassedDate;
	private Date ohcPassedDate;
	private Double baseAmount = new Double(0);
	private Double tds= new Double(0);	
	private Double payable= new Double(0);
	private String withHoldingTax;
	private String miroDescription;
	private Double roundOffAmount;
	private Double roundOffValue;
	private String cancelReason;
	private String postingDate;
//	private SecurityPOHeader securityPOHeader;
	private Date date_103;
	private User gateinPostedby;
	private User grnPostedby;
	private User gateinBy;
	private User reportedBy;
	private String netWeight;
	private User closedBy;
	private User createdBy;
	private String sap103Id;
	private String sap103Year;
	private String plant;
	public String doctyp;
	private Date servicePostingDate;
	private Date serviceFromDate;
	private Date serviceToDate;
	public String serviceLocation;
	private User ssnApprovedBy;
	private Date ssnApprovedDate;
//	public String ssnApproverId;
	
	
	
	@Id
	@SequenceGenerator(name = "t_advance_shipment_notice_seq", sequenceName = "t_advance_shipment_notice_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_advance_shipment_notice_seq")
	@Column(name="t_advance_shipment_notice_id",updatable = false)
	public Long getAdvanceShipmentNoticeId() {
		return advanceShipmentNoticeId;
	}
	public void setAdvanceShipmentNoticeId(Long advanceShipmentNoticeId) {
		this.advanceShipmentNoticeId = advanceShipmentNoticeId;
	}
	
	@Column(name="advance_shipment_notice_no")
	public Integer getAdvanceShipmentNoticeNo() {
		return advanceShipmentNoticeNo;
	}
	public void setAdvanceShipmentNoticeNo(Integer advanceShipmentNoticeNo) {
		this.advanceShipmentNoticeNo = advanceShipmentNoticeNo;
	}
	@Column(name="invoice_no")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@Column(name="invoice_date")
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	@Column(name="delivery_note_date")
	public Date getDeliveryNoteDate() {
		return deliveryNoteDate;
	}
	public void setDeliveryNoteDate(Date deliveryNoteDate) {
		this.deliveryNoteDate = deliveryNoteDate;
	}
	
	@Column(name="invoice_no_date")
	public Date getInvoiceNoDate() {
		return invoiceNoDate;
	}
	public void setInvoiceNoDate(Date invoiceNoDate) {
		this.invoiceNoDate = invoiceNoDate;
	}
	
	@Column(name="lr_date")
	public Date getLrDate() {
		return lrDate;
	}
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}
	
	@Column(name="invoice_amount")
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	@Column(name="mistach_amount")
	public Double getMismatchAmount() {
		return mismatchAmount;
	}
	public void setMismatchAmount(Double mismatchAmount) {
		this.mismatchAmount = mismatchAmount;
	}
	@Column(name="delivery_note_no")
	public String getDeliveryNoteNo() {
		return deliveryNoteNo;
	}
	public void setDeliveryNoteNo(String deliveryNoteNo) {
		this.deliveryNoteNo = deliveryNoteNo;
	}
	
	@Column(name="lr_number")
	public String getLrNumber() {
		return lrNumber;
	}
	public void setLrNumber(String lrNumber) {
		this.lrNumber = lrNumber;
	}
	@Column(name="transporter_no")
	public String getTransporterNo() {
		return transporterNo;
	}
	public void setTransporterNo(String transporterNo) {
		this.transporterNo = transporterNo;
	}
	@Column(name="vehical_no")
	public String getVehicalNo() {
		return vehicalNo;
	}
	public void setVehicalNo(String vehicalNo) {
		this.vehicalNo = vehicalNo;
	}
	@Column(name="e_way_bill_no")
	public String geteWayBillNo() {
		return eWayBillNo;
	}
	public void seteWayBillNo(String eWayBillNo) {
		this.eWayBillNo = eWayBillNo;
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
	public Double getNumberOfPackages() {
		return numberOfPackages;
	}
	public void setNumberOfPackages(Double numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}
	@Column(name="is_coa")
	public String getIsCOA() {
		return isCOA;
	}
	public void setIsCOA(String isCOA) {
		this.isCOA = isCOA;
	}
	@Column(name="is_packing_list")
	public String getIsPackingList() {
		return isPackingList;
	}
	public void setIsPackingList(String isPackingList) {
		this.isPackingList = isPackingList;
	}
	@Column(name="type_of_packing_bulk")
	public String getTypeOfPackingBulk() {
		return typeOfPackingBulk;
	}
	public void setTypeOfPackingBulk(String typeOfPackingBulk) {
		this.typeOfPackingBulk = typeOfPackingBulk;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_purchase_order_id",referencedColumnName="t_purchase_order_id")
	public PurchaseOrder getPo() {
		return po;
	}
	public void setPo(PurchaseOrder po) {
		this.po = po;
	}
	
	@Column(name="igst")
	public Double getIgst() {
		return igst;
	}
	public void setIgst(Double igst) {
		this.igst = igst;
	}
	@Column(name="sgst")
	public Double getSgst() {
		return sgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	
	@Column(name="cgst")
	public Double getCgst() {
		return cgst;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	
	@Column(name="freight_charges")
	public Double getFreightCharges() {
		return freightCharges;
	}
	public void setFreightCharges(Double freightCharges) {
		this.freightCharges = freightCharges;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invoice_doc", referencedColumnName="m_attachment_id")
	public Attachment getInvoice() {
		return invoice;
	}
	public void setInvoice(Attachment invoice) {
		this.invoice = invoice;
	}
	
	@Column(name="packing_charges")
	public Double getPackingCharges() {
		return packingCharges;
	}
	public void setPackingCharges(Double packingCharges) {
		this.packingCharges = packingCharges;
	}
	
	@Column(name="basic_amount")
	public Double getBasicAmount() {
		return basicAmount;
	}
	public void setBasicAmount(Double basicAmount) {
		this.basicAmount = basicAmount;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	@Column(name="irn")
	public String getIrn() {
		return irn;
	}
	public void setIrn(String irn) {
		this.irn = irn;
	}
	
	@Column(name="loading_charges")
	public Double getLoadingUnloadingCharges() {
		return loadingUnloadingCharges;
	}
	public void setLoadingUnloadingCharges(Double loadingUnloadingCharges) {
		this.loadingUnloadingCharges = loadingUnloadingCharges;
	}
	@Column(name="invoice_applicable")
	public String getInvoiceApplicable() {
		return invoiceApplicable;
	}
	public void setInvoiceApplicable(String invoiceApplicable) {
		this.invoiceApplicable = invoiceApplicable;
	}
	
	@Column(name="grn_year")
	public String getGrnYear() {
		return grnYear;
	}
	public void setGrnYear(String grnYear) {
		this.grnYear = grnYear;
	}
	
	@Column(name="grn_id")
	public String getGrnId() {
		return grnId;
	}
	public void setGrnId(String grnId) {
		this.grnId = grnId;
	}
	
	@Column(name="reject_reason")
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	@Column(name="issue_date")
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	@Column(name="isgrn")
	public String getIsGrn() {
		return isGrn;
	}
	public void setIsGrn(String isGrn) {
		this.isGrn = isGrn;
	}
	
	@Column(name="isgateout")
	public String getIsGateOut() {
		return isGateOut;
	}
	public void setIsGateOut(String isGateOut) {
		this.isGateOut = isGateOut;
	}
	
	@Column(name="isqcpassed")
	public String getIsQCPassed() {
		return isQCPassed;
	}
	public void setIsQCPassed(String isQCPassed) {
		this.isQCPassed = isQCPassed;
	}
	
	@Column(name="isunload")
	public String getIsUnload() {
		return isUnload;
	}
	public void setIsUnload(String isUnload) {
		this.isUnload = isUnload;
	}
	
	@Column(name="isgatein")
	public String getIsGateIn() {
		return isGateIn;
	}
	public void setIsGateIn(String isGateIn) {
		this.isGateIn = isGateIn;
	}
	
	@Column(name="isreported")
	public String getIsReported() {
		return isreported;
	}
	public void setIsReported(String isreported) {
		this.isreported = isreported;
	}
	
	@Column(name="issafetypassed")
	public String getIsSafetyPassed() {
		return isSafetyPassed;
	}
	public void setIsSafetyPassed(String isSafetyPassed) {
		this.isSafetyPassed = isSafetyPassed;
	}
	
	@Column(name="isohcpassed")
	public String getIsOHCPassed() {
		return isOHCPassed;
	}
	public void setIsOHCPassed(String isOHCPassed) {
		this.isOHCPassed = isOHCPassed;
	}
	
	@Column(name="grn_date")
	public Date getGrnDate() {
		return grnDate;
	}
	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}
	
	@Column(name="gateout_date")
	public Date getGateOutDate() {
		return gateOutDate;
	}
	public void setGateOutDate(Date gateOutDate) {
		this.gateOutDate = gateOutDate;
	}
	
	@Column(name="qcpassed_date")
	public Date getQcPassedDate() {
		return qcPassedDate;
	}
	public void setQcPassedDate(Date qcPassedDate) {
		this.qcPassedDate = qcPassedDate;
	}
	
	@Column(name="unload_date")
	public Date getUnloadDate() {
		return unloadDate;
	}
	public void setUnloadDate(Date unloadDate) {
		this.unloadDate = unloadDate;
	}
	
	@Column(name="gatein_date")
	public Date getGateInDate() {
		return gateInDate;
	}
	public void setGateInDate(Date gateInDate) {
		this.gateInDate = gateInDate;
	}
	
	@Column(name="reported_date")
	public Date getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	
	@Column(name="safteypassed_date")
	public Date getSafetyPassedDate() {
		return safetyPassedDate;
	}
	public void setSafetyPassedDate(Date safetyPassedDate) {
		this.safetyPassedDate = safetyPassedDate;
	}
	@Column(name="ohc_date")
	public Date getOhcPassedDate() {
		return ohcPassedDate;
	}
	public void setOhcPassedDate(Date ohcPassedDate) {
		this.ohcPassedDate = ohcPassedDate;
	}
	
	@Column(name="service_sheet_no")
	public String getServiceSheetNo() {
		return serviceSheetNo;
	}
	public void setServiceSheetNo(String serviceSheetNo) {
		this.serviceSheetNo = serviceSheetNo;
	}
	
	@Column(name="base_amount")
	public Double getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(Double baseAmount) {
		this.baseAmount = baseAmount;
	}
	
	@Column(name="tds_amount")
	public Double getTds() {
		return tds;
	}
	public void setTds(Double tds) {
		this.tds = tds;
	}
	
	@Column(name="payable_amount")
	public Double getPayable() {
		return payable;
	}
	public void setPayable(Double payable) {
		this.payable = payable;
	}
	
	@Column(name="with_holding_tax_code")
	public String getWithHoldingTax() {
		return withHoldingTax;
	}
	public void setWithHoldingTax(String withHoldingTax) {
		this.withHoldingTax = withHoldingTax;
	}
	@Column(name="miro_description")
	public String getMiroDescription() {
		return miroDescription;
	}
	public void setMiroDescription(String miroDescription) {
		this.miroDescription = miroDescription;
	}
	@Column(name="tcs")
	public Double getTcs() {
		return tcs;
	}
	public void setTcs(Double tcs) {
		this.tcs = tcs;
	}
	@Column(name="round_off_amount")
	public Double getRoundOffAmount() {
		return roundOffAmount;
	}
	public void setRoundOffAmount(Double roundOffAmount) {
		this.roundOffAmount = roundOffAmount;
	}
	@Column(name="round_off_value")
	public Double getRoundOffValue() {
		return roundOffValue;
	}
	public void setRoundOffValue(Double roundOffValue) {
		this.roundOffValue = roundOffValue;
	}
	@Column(name="cancel_reason")
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	@Column(name="posting_date")
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}


//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="t_asn_header_id",referencedColumnName="t_asn_header_id")
//	public SecurityPOHeader getSecurityPOHeader() {
//		return securityPOHeader;
//	}
//	public void setSecurityPOHeader(SecurityPOHeader securityPOHeader) {
//		this.securityPOHeader = securityPOHeader;
//	}
	
	@Column(name="date_103")
	public Date getDate_103() {
		return date_103;
	}
	public void setDate_103(Date date_103) {
		this.date_103 = date_103;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gatein_posted_by",referencedColumnName="ad_user_id")

	public User getGateinPostedby() {
		return gateinPostedby;
	}
	public void setGateinPostedby(User gateinPostedby) {
		this.gateinPostedby = gateinPostedby;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="grn_posted_by",referencedColumnName="ad_user_id")
	public User getGrnPostedby() {
		return grnPostedby;
	}
	
	public void setGrnPostedby(User grnPostedby) {
		this.grnPostedby = grnPostedby;
	}
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gatein_by",referencedColumnName="ad_user_id")	

	public User getGateinBy() {
		return gateinBy;
	}
	public void setGateinBy(User gateinBy) {
		this.gateinBy = gateinBy;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reportedby",referencedColumnName="ad_user_id")
	
	public User getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(User reportedBy) {
		this.reportedBy = reportedBy;
	}
	
	@Column(name="net_weight")
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="closedby",referencedColumnName="ad_user_id")
	public User getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(User closedBy) {
		this.closedBy = closedBy;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="createdBy",referencedColumnName="ad_user_id")
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	
		
	@Column(name="sap_103_id")
	public String getSap103Id() {
		return sap103Id;
	}
	
	
	public void setSap103Id(String sap103Id) {
		this.sap103Id = sap103Id;
	}
	
	@Column(name="sap_103_year")
	public String getSap103Year() {
		return sap103Year;
	}
	
	public void setSap103Year(String sap103Year) {
		this.sap103Year = sap103Year;
	}
	
	@Column(name="plant")
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	
	@Column(name="doctyp")
	public String getDoctyp() {
		return doctyp;
	}
	public void setDoctyp(String doctyp) {
		this.doctyp = doctyp;
	}
	
	
	@Column(name="service_posting_date")
	public Date getServicePostingDate() {
		return servicePostingDate;
	}
	
	public void setServicePostingDate(Date servicePostingDate) {
		this.servicePostingDate = servicePostingDate;
	}
	
	@Column(name="service_fromdate")
	public Date getServiceFromDate() {
		return serviceFromDate;
	}
	
	public void setServiceFromDate(Date serviceFromDate) {
		this.serviceFromDate = serviceFromDate;
	}
	
	@Column(name="service_todate")
	public Date getServiceToDate() {
		return serviceToDate;
	}
	
	public void setServiceToDate(Date serviceToDate) {
		this.serviceToDate = serviceToDate;
	}
	
	@Column(name="service_location")
	public String getServiceLocation() {
		return serviceLocation;
	}

	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ssn_approvedby",referencedColumnName="ad_user_id")	
	public User getSsnApprovedBy() {
		return ssnApprovedBy;
	}
	public void setSsnApprovedBy(User ssnApprovedBy) {
		this.ssnApprovedBy = ssnApprovedBy;
	}
	
	
	@Column(name="ssn_approveddate")
	public Date getSsnApprovedDate() {
		return ssnApprovedDate;
	}
	
	public void setSsnApprovedDate(Date ssnApprovedDate) {
		this.ssnApprovedDate = ssnApprovedDate;
	}
	
	
	
//	@Column(name="ssn_approver_id")
//	public String getSsnApproverId() {
//		return ssnApproverId;
//	}
//	public void setSsnApproverId(String ssnApproverId) {
//		this.ssnApproverId = ssnApproverId;
//	}
	

}
