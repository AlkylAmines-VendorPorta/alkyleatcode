package com.novelerp.alkyl.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.util.DateUtil;

public class SecurityPOHeaderDto extends CommonContextDto{
	private static final long serialVersionUID = 1L;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="asnHeaderId",scope=Long.class)
	private Long asnHeaderId;
//	private String poNumber;
//	private Integer asnNumber;
//	private Date asnDate;
	private Integer advanceShipmentNoticeNo;
	private String status;
	private String transporterNo;
	private String lrNumber;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date lrDate;
	private String vehicalNo;
	private String grossWeight;
	private String tareWeight;
	private String numberOfPackages;
	private String netWeight;
	private UserDto user;	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date invoiceDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date postingDate;
	private String invoiceNo;
	private PurchaseOrderDto po;
	private List<SecurityPOItemDto> asnLineList;
	private String nameOfDriver;	
	private String mobileNumber;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date reportedDate;
	private AttachmentDto photoIdProofAtt;
	private String photoIdProof;
	private UserDto reportedBy;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateinDate;
	private UserDto gateinBy;
//	 private UserDto createdBy;
	private UserDto createdBy;

	
	public Long getAsnHeaderId() {
		return asnHeaderId;
	}
//	public Integer getAsnNumber() {
//		return asnNumber;
//	}
	public String getStatus() {
		return status;
	}

	public String getLrNumber() {
		return lrNumber;
	}
	public Date getLrDate() {
		return lrDate;
	}

	public String getGrossWeight() {
		return grossWeight;
	}
	public String getTareWeight() {
		return tareWeight;
	}
	public String getNumberOfPackages() {
		return numberOfPackages;
	}
	public String getNetWeight() {
		return netWeight;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
//	public Date getCreateddate() {
//		return createddate;
//	}
	public PurchaseOrderDto getPo() {
		return po;
	}
	public void setAsnHeaderId(Long asnHeaderId) {
		this.asnHeaderId = asnHeaderId;
	}
//	public void setAsnNumber(Integer asnNumber) {
//		this.asnNumber = asnNumber;
//	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void setLrNumber(String lrNumber) {
		this.lrNumber = lrNumber;
	}
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	public void setTareWeight(String tareWeight) {
		this.tareWeight = tareWeight;
	}
	public void setNumberOfPackages(String numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
//	public void setCreateddate(Date createddate) {
//		this.createddate = createddate;
//	}
	public void setPo(PurchaseOrderDto po) {
		this.po = po;
	}
	public List<SecurityPOItemDto> getAsnLineList() {
		return asnLineList;
	}
	public void setAsnLineList(List<SecurityPOItemDto> asnLineList) {
		this.asnLineList = asnLineList;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getNameOfDriver() {
		return nameOfDriver;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setNameOfDriver(String nameOfDriver) {
		this.nameOfDriver = nameOfDriver;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Date getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	public AttachmentDto getPhotoIdProofAtt() {
		return photoIdProofAtt;
	}
	public void setPhotoIdProofAtt(AttachmentDto photoIdProofAtt) {
		this.photoIdProofAtt = photoIdProofAtt;
	}
	public String getPhotoIdProof() {
		return photoIdProof;
	}
	
	public void setPhotoIdProof(String photoIdProof) {
		this.photoIdProof = photoIdProof;
	}
	public UserDto getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(UserDto reportedBy) {
		this.reportedBy = reportedBy;
	}
	public Date getGateinDate() {
		return gateinDate;
	}
	public void setGateinDate(Date gateinDate) {
		this.gateinDate = gateinDate;
	}
	
	public UserDto getGateinBy() {
		return gateinBy;
	}
	public void setGateinBy(UserDto gateinBy) {
		this.gateinBy = gateinBy;
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
	public UserDto getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getAdvanceShipmentNoticeNo() {
		return advanceShipmentNoticeNo;
	}
	public void setAdvanceShipmentNoticeNo(Integer advanceShipmentNoticeNo) {
		this.advanceShipmentNoticeNo = advanceShipmentNoticeNo;
	}

	


	
	
}
