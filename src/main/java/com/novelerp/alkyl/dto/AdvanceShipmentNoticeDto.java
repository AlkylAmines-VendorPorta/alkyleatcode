package com.novelerp.alkyl.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;

public class AdvanceShipmentNoticeDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serviceSheetNo;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="advanceShipmentNoticeId",scope=AdvanceShipmentNoticeDto.class)
	private Long advanceShipmentNoticeId;
	private Integer advanceShipmentNoticeNo;
	private Date createddate;
	
	private String invoiceNo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date invoiceDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date deliveryNoteDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date invoiceNoDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date lrDate;
	//private String createdby;
	
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
	private PurchaseOrderDto po;
	
	private List<AdvanceShipmentNoticeLineDto> asnLineList;

	private Double igst;

	private Double sgst;

	private Double cgst;
	
	private Double tcs;

	private Double freightCharges;

	private AttachmentDto invoice;
	
	private Double packingCharges;
	
	private Double basicAmount;
	
	private String status;

	private AttachmentDto photoIdProofAtt;

	private String photoIdProof;

	private String mobileNumber;

	private String nameOfDriver;

	private String irn;
	
	private Double loadingUnloadingCharges;
	
	private String invoiceApplicable;
	private String grnYear;
	private String grnId;
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
	private String user;
	private String pass;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date grnDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateOutDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date qcPassedDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date unloadDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateInDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date reportedDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date safetyPassedDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date ohcPassedDate;

	private Double baseAmount;

	private Double tds;

	private Double payable;

	private String withHoldingTax;
	
	private String miroDescription;
	private Double roundOffAmount;
	private Double roundOffValue;
	private String cancelReason;
	private Double invoiceAmountByUser=(double) 0;
	private String postingDate;
//	private SecurityPOHeaderDto securityPOHeader;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date date_103;
	private UserDto gateinPostedby;
	private UserDto grnPostedby;
	private UserDto gateinBy;
	private UserDto reportedBy;
	private String netWeight;
	private UserDto closedBy;
	private String sap103Id;
	private String sap103Year;
	private String batchNo;
	private UserDto createdBy;
	private String plant;
	public String doctyp;
	public String HEADER;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date servicePostingDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date serviceFromDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date serviceToDate;
	public String serviceLocation;
	private UserDto ssnApprovedBy;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date ssnApprovedDate;
//	public String ssnApproverId;
	
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	public Double getIgst() {
		return igst;
	}
	public void setIgst(Double igst) {
		this.igst = igst;
	}
	public Double getSgst() {
		return sgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	public Double getCgst() {
		return cgst;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	public Double getFreightCharges() {
		return freightCharges;
	}
	public void setFreightCharges(Double freightCharges) {
		this.freightCharges = freightCharges;
	}
	public AttachmentDto getInvoice() {
		return invoice;
	}
	public void setInvoice(AttachmentDto invoice) {
		this.invoice = invoice;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getAdvanceShipmentNoticeId() {
		return advanceShipmentNoticeId;
	}
	public void setAdvanceShipmentNoticeId(Long advanceShipmentNoticeId) {
		this.advanceShipmentNoticeId = advanceShipmentNoticeId;
	}
	public Integer getAdvanceShipmentNoticeNo() {
		return advanceShipmentNoticeNo;
	}
	public void setAdvanceShipmentNoticeNo(Integer advanceShipmentNoticeNo) {
		this.advanceShipmentNoticeNo = advanceShipmentNoticeNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getDeliveryNoteDate() {
		return deliveryNoteDate;
	}
	public void setDeliveryNoteDate(Date deliveryNoteDate) {
		this.deliveryNoteDate = deliveryNoteDate;
	}
	public Date getInvoiceNoDate() {
		return invoiceNoDate;
	}
	public void setInvoiceNoDate(Date invoiceNoDate) {
		this.invoiceNoDate = invoiceNoDate;
	}
	public Date getLrDate() {
		return lrDate;
	}
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public Double getMismatchAmount() {
		return mismatchAmount;
	}
	public void setMismatchAmount(Double mismatchAmount) {
		this.mismatchAmount = mismatchAmount;
	}
	public String getDeliveryNoteNo() {
		return deliveryNoteNo;
	}
	public void setDeliveryNoteNo(String deliveryNoteNo) {
		this.deliveryNoteNo = deliveryNoteNo;
	}
	public String getLrNumber() {
		return lrNumber;
	}
	public void setLrNumber(String lrNumber) {
		this.lrNumber = lrNumber;
	}
	public String getTransporterNo() {
		return transporterNo;
	}
	public void setTransporterNo(String transporterNo) {
		this.transporterNo = transporterNo;
	}
	public String getVehicalNo() {
		return vehicalNo;
	}
	public void setVehicalNo(String vehicalNo) {
		this.vehicalNo = vehicalNo;
	}
	public String geteWayBillNo() {
		return eWayBillNo;
	}
	public void seteWayBillNo(String eWayBillNo) {
		this.eWayBillNo = eWayBillNo;
	}
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getTareWeight() {
		return tareWeight;
	}
	public void setTareWeight(String tareWeight) {
		this.tareWeight = tareWeight;
	}
	public Double getNumberOfPackages() {
		return numberOfPackages;
	}
	public void setNumberOfPackages(Double numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}
	public String getIsCOA() {
		if(CommonUtil.isStringEmpty(this.isCOA)){
			return "N";
		}
		return isCOA;
	}
	public void setIsCOA(String isCOA) {
		this.isCOA = isCOA;
	}
	public String getIsPackingList() {
		if(CommonUtil.isStringEmpty(this.isPackingList)){
			return "N";
		}
		return isPackingList;
	}
	public void setIsPackingList(String isPackingList) {
		this.isPackingList = isPackingList;
	}
	public String getTypeOfPackingBulk() {
		return typeOfPackingBulk;
	}
	public void setTypeOfPackingBulk(String typeOfPackingBulk) {
		this.typeOfPackingBulk = typeOfPackingBulk;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public PurchaseOrderDto getPo() {
		return po;
	}
	public void setPo(PurchaseOrderDto po) {
		this.po = po;
	}
	public List<AdvanceShipmentNoticeLineDto> getAsnLineList() {
		return asnLineList;
	}
	public void setAsnLineList(List<AdvanceShipmentNoticeLineDto> asnLineList) {
		this.asnLineList = asnLineList;
	}
	public Double getPackingCharges() {
		return packingCharges;
	}
	public void setPackingCharges(Double packingCharges) {
		this.packingCharges = packingCharges;
	}
	public Double getBasicAmount() {
		return basicAmount;
	}
	public void setBasicAmount(Double basicAmount) {
		this.basicAmount = basicAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNameOfDriver() {
		return nameOfDriver;
	}
	public void setNameOfDriver(String nameOfDriver) {
		this.nameOfDriver = nameOfDriver;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPhotoIdProof() {
		return photoIdProof;
	}
	public void setPhotoIdProof(String photoIdProof) {
		this.photoIdProof = photoIdProof;
	}
	public AttachmentDto getPhotoIdProofAtt() {
		return photoIdProofAtt;
	}
	public void setPhotoIdProofAtt(AttachmentDto photoIdProofAtt) {
		this.photoIdProofAtt = photoIdProofAtt;
	}
	public String getIrn() {
		return irn;
	}
	public void setIrn(String irn) {
		this.irn = irn;
	}
	public Double getLoadingUnloadingCharges() {
		return loadingUnloadingCharges;
	}
	public void setLoadingUnloadingCharges(Double loadingUnloadingCharges) {
		this.loadingUnloadingCharges = loadingUnloadingCharges;
	}
	public String getInvoiceApplicable() {
		return invoiceApplicable;
	}
	public void setInvoiceApplicable(String invoiceApplicable) {
		this.invoiceApplicable = invoiceApplicable;
	}
	
	public String getGrnYear() {
		return grnYear;
	}
	public void setGrnYear(String grnYear) {
		this.grnYear = grnYear;
	}
	
	public String getGrnId() {
		return grnId;
	}
	public void setGrnId(String grnId) {
		this.grnId = grnId;
	}
	
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getuser() {
		return user;
	}
	public void setuser(String user) {
		this.user = user;
	}
	public String getpass() {
		return pass;
	}
	public void setpass(String pass) {
		this.pass = pass;
	}
	public String getIsGrn() {
		if(isGrn==null){
			
			return "N";
		}
		else{
			return isGrn;
		}
	}
	public void setIsGrn(String isGrn) {
		this.isGrn = isGrn;
	}
	public String getIsGateOut() {
		if(isGateOut==null){			
			return "N";
		}
		else{
		return isGateOut;
	}
}
	public void setIsGateOut(String isGateOut) {
		this.isGateOut = isGateOut;
	}
	public String getIsQCPassed() {
		if(isQCPassed==null){
			return "N";
		}
		else{
			return isQCPassed;
		}
	}
	public void setIsQCPassed(String isQCPassed) {
		this.isQCPassed = isQCPassed;
	}
	public String getIsUnload() {
		if(isUnload==null){
			return "N";
		}
		else{
			return isUnload;
		}
	}
	public void setIsUnload(String isUnload) {
		this.isUnload = isUnload;
	}
	public String getIsGateIn() {
		if(isGateIn==null){
			return "N";
		}
		else{
			return isGateIn;
		}
	}
	public void setIsGateIn(String isGateIn) {
		this.isGateIn = isGateIn;
	}
	public String getIsReported() {
		if(isreported==null){
			return "N";
		}
		else {
			return isreported;
		}
	}
	public void setIsReported(String isreported) {
		this.isreported = isreported;
	}
	public String getIsSafetyPassed() {
		if(isSafetyPassed==null){
			
			return "N";
		}else{
			return isSafetyPassed;
		}
	}
	public void setIsSafetyPassed(String isSafetyPassed) {
		this.isSafetyPassed = isSafetyPassed;
	}
	public String getIsOHCPassed() {
		if(isOHCPassed==null){
			return "N";
		}
		else{
			return isOHCPassed;
		}
	}
	public void setIsOHCPassed(String isOHCPassed) {
		this.isOHCPassed = isOHCPassed;
	}
	public Date getGrnDate() {
		return grnDate;
	}
	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}
	public Date getGateOutDate() {
		return gateOutDate;
	}
	public void setGateOutDate(Date gateOutDate) {
		this.gateOutDate = gateOutDate;
	}
	public Date getQcPassedDate() {
		return qcPassedDate;
	}
	public void setQcPassedDate(Date qcPassedDate) {
		this.qcPassedDate = qcPassedDate;
	}
	public Date getUnloadDate() {
		return unloadDate;
	}
	public void setUnloadDate(Date unloadDate) {
		this.unloadDate = unloadDate;
	}
	public Date getGateInDate() {
		return gateInDate;
	}
	public void setGateInDate(Date gateInDate) {
		this.gateInDate = gateInDate;
	}
	public Date getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	public Date getSafetyPassedDate() {
		return safetyPassedDate;
	}
	public void setSafetyPassedDate(Date safetyPassedDate) {
		this.safetyPassedDate = safetyPassedDate;
	}
	public Date getOhcPassedDate() {
		return ohcPassedDate;
	}
	public void setOhcPassedDate(Date ohcPassedDate) {
		this.ohcPassedDate = ohcPassedDate;
	}
	public String getServiceSheetNo() {
		return serviceSheetNo;
	}
	public void setServiceSheetNo(String serviceSheetNo) {
		this.serviceSheetNo = serviceSheetNo;
	}
	
	public Double getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(Double baseAmount) {
		this.baseAmount = baseAmount;
	}
	
	public Double getTds() {
		return tds;
	}
	public void setTds(Double tds) {
		this.tds = tds;
	}
	
	public Double getPayable() {	
		return payable;
	}
	public void setPayable(Double payable) {
		this.payable = payable;
	}
	
	public String getWithHoldingTax() {
		return withHoldingTax;
	}
	public void setWithHoldingTax(String withHoldingTax) {
		this.withHoldingTax = withHoldingTax;
	}
	public String getMiroDescription() {
		return miroDescription;
	}
	public void setMiroDescription(String miroDescription) {
		this.miroDescription = miroDescription;
	}
	public Double getTcs() {
		return tcs;
	}
	public void setTcs(Double tcs) {
		this.tcs = tcs;
	}
	public Double getRoundOffAmount() {
		return roundOffAmount;
	}
	public void setRoundOffAmount(Double roundOffAmount) {
		this.roundOffAmount = roundOffAmount;
	}
	public Double getRoundOffValue() {
		return roundOffValue;
	}
	public void setRoundOffValue(Double roundOffValue) {
		this.roundOffValue = roundOffValue;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public Double getInvoiceAmountByUser() {
		return invoiceAmountByUser;
	}
	public void setInvoiceAmountByUser(Double invoiceAmountByUser) {
		this.invoiceAmountByUser = invoiceAmountByUser;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
//	public String getCreatedby() {
//		return createdby;
//	}
//	public void setCreatedby(String createdby) {
//		this.createdby = createdby;
//	}
	
	
//	public SecurityPOHeaderDto getSecurityPOHeader() {
//		return securityPOHeader;
//	}
//	public void setSecurityPOHeader(SecurityPOHeaderDto securityPOHeader) {
//		this.securityPOHeader = securityPOHeader;
//	}
	public Date getDate_103() {
		return date_103;
	}
	public UserDto getGateinPostedby() {
		return gateinPostedby;
	}
	public UserDto getGrnPostedby() {
		return grnPostedby;
	}
	public void setDate_103(Date date_103) {
		this.date_103 = date_103;
	}
	public void setGateinPostedby(UserDto gateinPostedby) {
		this.gateinPostedby = gateinPostedby;
	}
	public void setGrnPostedby(UserDto grnPostedby) {
		this.grnPostedby = grnPostedby;
	}
	
	
	
	public UserDto getGateinBy() {
		return gateinBy;
	}
	public UserDto getReportedBy() {
		return reportedBy;
	}
	public void setGateinBy(UserDto gateinBy) {
		this.gateinBy = gateinBy;
	}
	public void setReportedBy(UserDto reportedBy) {
		this.reportedBy = reportedBy;
	}
	
	
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	
	public UserDto getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(UserDto closedBy) {
		this.closedBy = closedBy;
	}
	
	
	public String getSap103Id() {
		return sap103Id;
	}
	public String getSap103Year() {
		return sap103Year;
	}
	public void setSap103Id(String sap103Id) {
		this.sap103Id = sap103Id;
	}
	public void setSap103Year(String sap103Year) {
		this.sap103Year = sap103Year;
	}
	
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public UserDto getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
		
	public String getDoctyp() {
		return doctyp;
	}
	public void setDoctyp(String doctyp) {
		this.doctyp = doctyp;
	}
	
	public String getHEADER() {
		return HEADER;
	}
	public void setHEADER(String hEADER) {
		HEADER = hEADER;
	}
	
	
	
	public Date getServicePostingDate() {
		return servicePostingDate;
	}
	public Date getServiceFromDate() {
		return serviceFromDate;
	}
	public Date getServiceToDate() {
		return serviceToDate;
	}
	public String getServiceLocation() {
		return serviceLocation;
	}
	public void setServicePostingDate(Date servicePostingDate) {
		this.servicePostingDate = servicePostingDate;
	}
	public void setServiceFromDate(Date serviceFromDate) {
		this.serviceFromDate = serviceFromDate;
	}
	public void setServiceToDate(Date serviceToDate) {
		this.serviceToDate = serviceToDate;
	}
	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}
	
	
	public UserDto getSsnApprovedBy() {
		return ssnApprovedBy;
	}
	public Date getSsnApprovedDate() {
		return ssnApprovedDate;
	}
	public void setSsnApprovedBy(UserDto ssnApprovedBy) {
		this.ssnApprovedBy = ssnApprovedBy;
	}
	public void setSsnApprovedDate(Date ssnApprovedDate) {
		this.ssnApprovedDate = ssnApprovedDate;
	}
	
//	public String getSsnApproverId() {
//	return ssnApproverId;
//}
//public void setSsnApproverId(String ssnApproverId) {
//	this.ssnApproverId = ssnApproverId;
//}
	@Override
	public String toString() {
		return "AdvanceShipmentNoticeDto [serviceSheetNo=" + serviceSheetNo + ", advanceShipmentNoticeId="
				+ advanceShipmentNoticeId + ", advanceShipmentNoticeNo=" + advanceShipmentNoticeNo + ", createddate="
				+ createddate + ", invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", deliveryNoteDate="
				+ deliveryNoteDate + ", invoiceNoDate=" + invoiceNoDate + ", lrDate=" + lrDate + ", invoiceAmount="
				+ invoiceAmount + ", mismatchAmount=" + mismatchAmount + ", deliveryNoteNo=" + deliveryNoteNo
				+ ", lrNumber=" + lrNumber + ", transporterNo=" + transporterNo + ", vehicalNo=" + vehicalNo
				+ ", eWayBillNo=" + eWayBillNo + ", grossWeight=" + grossWeight + ", tareWeight=" + tareWeight
				+ ", numberOfPackages=" + numberOfPackages + ", isCOA=" + isCOA + ", isPackingList=" + isPackingList
				+ ", typeOfPackingBulk=" + typeOfPackingBulk + ", remarks=" + remarks + ", name=" + name + ", code="
				+ code + ", description=" + description + ", po=" + po + ", asnLineList=" + asnLineList + ", igst="
				+ igst + ", sgst=" + sgst + ", cgst=" + cgst + ", tcs=" + tcs + ", freightCharges=" + freightCharges
				+ ", invoice=" + invoice + ", packingCharges=" + packingCharges + ", basicAmount=" + basicAmount
				+ ", status=" + status + ", photoIdProofAtt=" + photoIdProofAtt + ", photoIdProof=" + photoIdProof
				+ ", mobileNumber=" + mobileNumber + ", nameOfDriver=" + nameOfDriver + ", irn=" + irn
				+ ", loadingUnloadingCharges=" + loadingUnloadingCharges + ", invoiceApplicable=" + invoiceApplicable
				+ ", grnYear=" + grnYear + ", grnId=" + grnId + ", rejectReason=" + rejectReason + ", issueDate="
				+ issueDate + ", isGrn=" + isGrn + ", isGateOut=" + isGateOut + ", isQCPassed=" + isQCPassed
				+ ", isUnload=" + isUnload + ", isGateIn=" + isGateIn + ", isreported=" + isreported
				+ ", isSafetyPassed=" + isSafetyPassed + ", isOHCPassed=" + isOHCPassed + ", user=" + user + ", pass="
				+ pass + ", grnDate=" + grnDate + ", gateOutDate=" + gateOutDate + ", qcPassedDate=" + qcPassedDate
				+ ", unloadDate=" + unloadDate + ", gateInDate=" + gateInDate + ", reportedDate=" + reportedDate
				+ ", safetyPassedDate=" + safetyPassedDate + ", ohcPassedDate=" + ohcPassedDate + ", baseAmount="
				+ baseAmount + ", tds=" + tds + ", payable=" + payable + ", withHoldingTax=" + withHoldingTax
				+ ", miroDescription=" + miroDescription + ", roundOffAmount=" + roundOffAmount + ", roundOffValue="
				+ roundOffValue + ", cancelReason=" + cancelReason + ", invoiceAmountByUser=" + invoiceAmountByUser
				+ ", postingDate=" + postingDate + ", date_103=" + date_103 + ", gateinPostedby=" + gateinPostedby
				+ ", grnPostedby=" + grnPostedby + ", gateinBy=" + gateinBy + ", reportedBy=" + reportedBy
				+ ", netWeight=" + netWeight + ", closedBy=" + closedBy + ", sap103Id=" + sap103Id + ", sap103Year="
				+ sap103Year + ", batchNo=" + batchNo + ", createdBy=" + createdBy + ", plant=" + plant + ", doctyp="
				+ doctyp + ", HEADER=" + HEADER + ", servicePostingDate=" + servicePostingDate + ", serviceFromDate="
				+ serviceFromDate + ", serviceToDate=" + serviceToDate + ", serviceLocation=" + serviceLocation
				+ ", ssnApprovedBy=" + ssnApprovedBy + ", ssnApprovedDate=" + ssnApprovedDate + "]";
	}
	
	
	

	
	
	
	
	
		}
	

	
	
	
	
	
	
	
	
	

	

	
	
	
	
	
	

