package com.novelerp.alkyl.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.util.DateUtil;

public class VehicleRegistrationDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long vehicleRegistationId;
	private String saleOrderNo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date requiredOn;
	private String plant;
	private String destination;
	private String materialCode;
	private String qty;
	private String vehicleType;
	private String route;
	private String freightScope;
	private String requestNo;
	private String status;
	private UserDto meCode;
	private String soldToParty;
	private String shipToParty;
	private String trasnporter;
	private String vehicleRegistrationNo;
	private String licenseNo;
	private String tripLSP;
	private String driverName;
	private String phoneNo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date expDate;
	private AttachmentDto driverPic;
	private AttachmentDto docPic;
	private String driverPicString;
	private String docPicString;
	private String invoiceNo;
	private String invoiceDate;
	private String transportEmail;
	private String shipToEmail;
	private String soldToEmail;
	private String gstin;
	private String consignee;
	private String uom;
	private String localTransporter;
	private String onwardTransporter;
	private String invoiceAmt;
	private String poNo;
	private String custNo;
	private String lrNo;
	private String filename;
	private String shiptogstin;
	private String soldtogstin;
	private String message;
	private Date reporteddate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateIndate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateOutdate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private boolean success;
	private UserDto reportedby;
	private UserDto gateInby;
	private UserDto gateOutby;
	private String remarks;
	private String fromPlant;
	private String toPlant;
	private String poDate;
	private String vehicalNo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date destinationgateindate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date destinationgateoutdate;
	private String Stts_flg;
	private String Err_cd;
	//private String message;
	
	
	public UserDto getGateOutby() {
		return gateOutby;
	}
	public void setGateOutby(UserDto gateOutby) {
		this.gateOutby = gateOutby;
	}
	public UserDto getGateInby() {
		return gateInby;
	}
	public void setGateInby(UserDto gateInby) {
		this.gateInby = gateInby;
	}
	public UserDto getReportedby() {
		return reportedby;
	}
	public void setReportedby(UserDto reportedby) {
		this.reportedby = reportedby;
	}
	public Date getGateOutdate() {
		return gateOutdate;
	}
	public void setGateOutdate(Date gateOutdate) {
		this.gateOutdate = gateOutdate;
	}
	public Date getGateIndate() {
		return gateIndate;
	}
	public void setGateIndate(Date gateIndate) {
		this.gateIndate = gateIndate;
	}
	public Date getReporteddate() {
		return reporteddate;
	}
	public void setReporteddate(Date reporteddate) {
		this.reporteddate = reporteddate;
	}
	public Long getVehicleRegistationId() {
		return vehicleRegistationId;
	}
	public void setVehicleRegistationId(Long vehicleRegistationId) {
		this.vehicleRegistationId = vehicleRegistationId;
	}
	public String getSaleOrderNo() {
		return saleOrderNo;
	}
	public void setSaleOrderNo(String saleOrderNo) {
		this.saleOrderNo = saleOrderNo;
	}
	public Date getRequiredOn() {
		return requiredOn;
	}
	public void setRequiredOn(Date requiredOn) {
		this.requiredOn = requiredOn;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getFreightScope() {
		return freightScope;
	}
	public void setFreightScope(String freightScope) {
		this.freightScope = freightScope;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserDto getMeCode() {
		return meCode;
	}
	public void setMeCode(UserDto meCode) {
		this.meCode = meCode;
	}

	public String getSoldToParty() {
		return soldToParty;
	}
	public void setSoldToParty(String soldToParty) {
		this.soldToParty = soldToParty;
	}
	public String getShipToParty() {
		return shipToParty;
	}
	public void setShipToParty(String shipToParty) {
		this.shipToParty = shipToParty;
	}
	public String getTrasnporter() {
		return trasnporter;
	}
	public void setTrasnporter(String trasnporter) {
		this.trasnporter = trasnporter;
	}
	public String getVehicleRegistrationNo() {
		return vehicleRegistrationNo;
	}
	public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
		this.vehicleRegistrationNo = vehicleRegistrationNo;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getTripLSP() {
		return tripLSP;
	}
	public void setTripLSP(String tripLSP) {
		this.tripLSP = tripLSP;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public AttachmentDto getDriverPic() {
		return driverPic;
	}
	public void setDriverPic(AttachmentDto driverPic) {
		this.driverPic = driverPic;
	}
	public AttachmentDto getDocPic() {
		return docPic;
	}
	public void setDocPic(AttachmentDto docPic) {
		this.docPic = docPic;
	}
	public String getDriverPicString() {
		return driverPicString;
	}
	public void setDriverPicString(String driverPicString) {
		this.driverPicString = driverPicString;
	}
	public String getDocPicString() {
		return docPicString;
	}
	public void setDocPicString(String docPicString) {
		this.docPicString = docPicString;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getTransportEmail() {
		return transportEmail;
	}
	public void setTransportEmail(String transportEmail) {
		this.transportEmail = transportEmail;
	}
	public String getShipToEmail() {
		return shipToEmail;
	}
	public void setShipToEmail(String shipToEmail) {
		this.shipToEmail = shipToEmail;
	}
	public String getSoldToEmail() {
		return soldToEmail;
	}
	public void setSoldToEmail(String soldToEmail) {
		this.soldToEmail = soldToEmail;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getLocalTransporter() {
		return localTransporter;
	}
	public void setLocalTransporter(String localTransporter) {
		this.localTransporter = localTransporter;
	}
	public String getOnwardTransporter() {
		return onwardTransporter;
	}
	public void setOnwardTransporter(String onwardTransporter) {
		this.onwardTransporter = onwardTransporter;
	}
	public String getInvoiceAmt() {
		return invoiceAmt;
	}
	public void setInvoiceAmt(String invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getLrNo() {
		return lrNo;
	}
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getShiptogstin() {
		return shiptogstin;
	}
	public void setShiptogstin(String shiptogstin) {
		this.shiptogstin = shiptogstin;
	}
	public String getSoldtogstin() {
		return soldtogstin;
	}
	public void setSoldtogstin(String soldtogstin) {
		this.soldtogstin = soldtogstin;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFromPlant() {
		return fromPlant;
	}
	public String getToPlant() {
		return toPlant;
	}
	public void setFromPlant(String fromPlant) {
		this.fromPlant = fromPlant;
	}
	public void setToPlant(String toPlant) {
		this.toPlant = toPlant;
	}
	public String getPoDate() {
		return poDate;
	}
	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}
	public String getVehicalNo() {
		return vehicalNo;
	}
	public void setVehicalNo(String vehicalNo) {
		this.vehicalNo = vehicalNo;
	}
	public Date getDestinationgateindate() {
		return destinationgateindate;
	}
	public Date getDestinationgateoutdate() {
		return destinationgateoutdate;
	}
	public void setDestinationgateindate(Date destinationgateindate) {
		this.destinationgateindate = destinationgateindate;
	}
	public void setDestinationgateoutdate(Date destinationgateoutdate) {
		this.destinationgateoutdate = destinationgateoutdate;
	}
	public String getStts_flg() {
		return Stts_flg;
	}
	public String getErr_cd() {
		return Err_cd;
	}
	public void setStts_flg(String stts_flg) {
		Stts_flg = stts_flg;
	}
	public void setErr_cd(String err_cd) {
		Err_cd = err_cd;
	}

	
	
	
	
}
