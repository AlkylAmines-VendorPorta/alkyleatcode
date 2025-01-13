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
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.util.DateUtil;

@Entity
@Table(name="t_vehicle_registration")
public class VehicleRegistration extends ContextPO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long vehicleRegistationId;
	private String saleOrderNo;
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
	private User meCode;
	private String soldToParty;
	private String shipToParty;
	private String trasnporter;
	private String vehicleRegistrationNo;
	private String licenseNo;
	private String tripLSP;
	private String driverName;
	private String phoneNo;
	private Date expDate;
	private Attachment driverPic;
	private Attachment docPic;
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
	private Date reporteddate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateIndate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateOutdate;
	private User reportedby;
	private User gateInby;
	private User gateOutby;
	private String remarks;
	private String fromPlant;
	private String toPlant;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date destinationgateindate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date destinationgateoutdate;
	
	
	@Id
	@SequenceGenerator(name = "t_vehicle_registration_seq", sequenceName = "t_vehicle_registration_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_vehicle_registration_seq")
	@Column(name="t_vehicle_registration_id",updatable = false)
	public Long getVehicleRegistationId() {
		return vehicleRegistationId;
	}
	public void setVehicleRegistationId(Long vehicleRegistationId) {
		this.vehicleRegistationId = vehicleRegistationId;
	}
	@Column(name="sales_order_no")
	public String getSaleOrderNo() {
		return saleOrderNo;
	}
	public void setSaleOrderNo(String saleOrderNo) {
		this.saleOrderNo = saleOrderNo;
	}
	@Column(name="required_on")
	public Date getRequiredOn() {
		return requiredOn;
	}
	public void setRequiredOn(Date requiredOn) {
		this.requiredOn = requiredOn;
	}
	@Column(name="plant")
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	@Column(name="destination")
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Column(name="material_code")
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	@Column(name="qty")
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	@Column(name="vehicle_type")
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	@Column(name="route")
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	@Column(name="freigt_scope")
	public String getFreightScope() {
		return freightScope;
	}
	public void setFreightScope(String freightScope) {
		this.freightScope = freightScope;
	}
	@Column(name="request_no")
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="meCode",referencedColumnName="ad_user_id")
//	public User getMeCode() {
//		return meCode;
//	}
//	public void setMeCode(User meCode) {
//		this.meCode = meCode;
//	}
	
	@Column(name="vehicle_registration_no")
	public String getVehicleRegistrationNo() {
		return vehicleRegistrationNo;
	}
	public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
		this.vehicleRegistrationNo = vehicleRegistrationNo;
	}
	@Column(name="license_no")
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	@Column(name="trip_lsp")
	public String getTripLSP() {
		return tripLSP;
	}
	public void setTripLSP(String tripLSP) {
		this.tripLSP = tripLSP;
	}
	@Column(name="driver_name")
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	@Column(name="phone_no")
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Column(name="exp_date")
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="driverPic",referencedColumnName="m_attachment_id")
	public Attachment getDriverPic() {
		return driverPic;
	}
	public void setDriverPic(Attachment driverPic) {
		this.driverPic = driverPic;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="docPic",referencedColumnName="m_attachment_id")
	public Attachment getDocPic() {
		return docPic;
	}
	public void setDocPic(Attachment docPic) {
		this.docPic = docPic;
	}
	@Column(name="driver_pic_string")
	public String getDriverPicString() {
		return driverPicString;
	}
	public void setDriverPicString(String driverPicString) {
		this.driverPicString = driverPicString;
	}
	@Column(name="doc_pic_string")
	public String getDocPicString() {
		return docPicString;
	}
	public void setDocPicString(String docPicString) {
		this.docPicString = docPicString;
	}
	@Column(name="invoice_no")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@Column(name="invoice_date")
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	@Column(name="transport_email")
	public String getTransportEmail() {
		return transportEmail;
	}
	public void setTransportEmail(String transportEmail) {
		this.transportEmail = transportEmail;
	}
	@Column(name="ship_to_email")
	public String getShipToEmail() {
		return shipToEmail;
	}
	public void setShipToEmail(String shipToEmail) {
		this.shipToEmail = shipToEmail;
	}
	@Column(name="sold_to_email")
	public String getSoldToEmail() {
		return soldToEmail;
	}
	public void setSoldToEmail(String soldToEmail) {
		this.soldToEmail = soldToEmail;
	}
	@Column(name="gstin")
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	@Column(name="consignee")
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	@Column(name="uom")
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	@Column(name="local_transporter")
	public String getLocalTransporter() {
		return localTransporter;
	}
	public void setLocalTransporter(String localTransporter) {
		this.localTransporter = localTransporter;
	}
	@Column(name="onward_transporter")
	public String getOnwardTransporter() {
		return onwardTransporter;
	}
	public void setOnwardTransporter(String onwardTransporter) {
		this.onwardTransporter = onwardTransporter;
	}
	@Column(name="invoice_amt")
	public String getInvoiceAmt() {
		return invoiceAmt;
	}
	public void setInvoiceAmt(String invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}
	@Column(name="po_no")
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	@Column(name="cust_no")
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	@Column(name="lr_no")
	public String getLrNo() {
		return lrNo;
	}
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}
	@Column(name="soldToParty")
	public String getSoldToParty() {
		return soldToParty;
	}
	public void setSoldToParty(String soldToParty) {
		this.soldToParty = soldToParty;
	}
	@Column(name="shipToParty")
	public String getShipToParty() {
		return shipToParty;
	}
	public void setShipToParty(String shipToParty) {
		this.shipToParty = shipToParty;
	}
	@Column(name="trasnporter")
	public String getTrasnporter() {
		return trasnporter;
	}
	public void setTrasnporter(String trasnporter) {
		this.trasnporter = trasnporter;
	}
	@Column(name="reporteddate")
	public Date getReporteddate() {
		return reporteddate;
	}
	public void setReporteddate(Date reporteddate) {
		this.reporteddate = reporteddate;
	}

	
	@Column(name="gateindate")
	public Date getGateIndate() {
		return gateIndate;
	}
	public void setGateIndate(Date gateIndate) {
		this.gateIndate = gateIndate;
	}
	
	@Column(name="gateoutdate")
	public Date getGateOutdate() {
		return gateOutdate;
	}
	public void setGateOutdate(Date gateOutdate) {
		this.gateOutdate = gateOutdate;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reportedby",referencedColumnName="ad_user_id")
	public User getReportedby() {
		return reportedby;
	}
	public void setReportedby(User reportedby) {
		this.reportedby = reportedby;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gateinby",referencedColumnName="ad_user_id")
	public User getGateInby() {
		return gateInby;
	}
	public void setGateInby(User gateInby) {
		this.gateInby = gateInby;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gateoutby",referencedColumnName="ad_user_id")
	public User getGateOutby() {
		return gateOutby;
	}
	public void setGateOutby(User gateOutby) {
		this.gateOutby = gateOutby;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="fromplant")
	
	public String getFromPlant() {
		return fromPlant;
	}
	public void setFromPlant(String fromPlant) {
		this.fromPlant = fromPlant;
	}
	
	@Column(name="toplant")
	public String getToPlant() {
		return toPlant;
	}
	
	public void setToPlant(String toPlant) {
		this.toPlant = toPlant;
	}
	

	
	
	@Column(name="destinationgateindate")
	public Date getDestinationgateindate() {
		return destinationgateindate;
	}
	
	public void setDestinationgateindate(Date destinationgateindate) {
		this.destinationgateindate = destinationgateindate;
	}

	
	@Column(name="destinationgateoutdate")
	public Date getDestinationgateoutdate() {
		return destinationgateoutdate;
	}
	
	public void setDestinationgateoutdate(Date destinationgateoutdate) {
		this.destinationgateoutdate = destinationgateoutdate;
	}
	
	

}
