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

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name="t_gate_entry")
public class GateEntry extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gateEntryId;
	private String reqNo;
	private String vendorName;
	private String vendorAddress;
	private String poNo;
	private String remark;
	private String status;
	private String docType;
	private String rejectDesc;
	private String hodRejectDesc;
	private String commRejectDesc;
	private String plant;
	private User hod;
	private Date hodDate;
	private User fh;
	private Date fhDate;
	private User commercial;
	private Date commercialDate;
	private User gateOut;
	private Date gateOutDate;
	private User closedBy;
	private Date closedDate;
	private String purpose;
	private Date returnBy;
	private String vehicleNo;
	private String vehicleType;
	private String transporterName;
	private String cancelDesc;
    private User planthead;
	private Date plantheadDate;
	
	@Id
	@SequenceGenerator(name = "t_gate_entry_seq", sequenceName = "t_gate_entry_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_gate_entry_seq")
	@Column(name="t_gate_entry_id",updatable = false)
	public Long getGateEntryId() {
		return gateEntryId;
	}
	public void setGateEntryId(Long gateEntryId) {
		this.gateEntryId = gateEntryId;
	}
	@Column(name="req_no")
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	@Column(name="vendor_name")
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	@Column(name="vendor_address")
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	@Column(name="po_no")
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="doc_type")
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	@Column(name="reject_desc")
	public String getRejectDesc() {
		return rejectDesc;
	}
	public void setRejectDesc(String rejectDesc) {
		this.rejectDesc = rejectDesc;
	}
	@Column(name="hod_reject_desc")
	public String getHodRejectDesc() {
		return hodRejectDesc;
	}
	public void setHodRejectDesc(String hodRejectDesc) {
		this.hodRejectDesc = hodRejectDesc;
	}
	@Column(name="comm_reject_desc")
	public String getCommRejectDesc() {
		return commRejectDesc;
	}
	public void setCommRejectDesc(String commRejectDesc) {
		this.commRejectDesc = commRejectDesc;
	}
	@Column(name="plant")
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hod",referencedColumnName="ad_user_id")
	public User getHod() {
		return hod;
	}
	public void setHod(User hod) {
		this.hod = hod;
	}
	@Column(name="hod_date")
	public Date getHodDate() {
		return hodDate;
	}
	public void setHodDate(Date hodDate) {
		this.hodDate = hodDate;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fh",referencedColumnName="ad_user_id")
	public User getFh() {
		return fh;
	}
	public void setFh(User fh) {
		this.fh = fh;
	}
	@Column(name="fh_date")
	public Date getFhDate() {
		return fhDate;
	}
	public void setFhDate(Date fhDate) {
		this.fhDate = fhDate;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="commercial",referencedColumnName="ad_user_id")
	public User getCommercial() {
		return commercial;
	}
	public void setCommercial(User commercial) {
		this.commercial = commercial;
	}
	@Column(name="commercial_date")
	public Date getCommercialDate() {
		return commercialDate;
	}
	public void setCommercialDate(Date commercialDate) {
		this.commercialDate = commercialDate;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="gate_out",referencedColumnName="ad_user_id")
	public User getGateOut() {
		return gateOut;
	}
	public void setGateOut(User gateOut) {
		this.gateOut = gateOut;
	}
	@Column(name="gate_out_date")
	public Date getGateOutDate() {
		return gateOutDate;
	}
	public void setGateOutDate(Date gateOutDate) {
		this.gateOutDate = gateOutDate;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="closed_by",referencedColumnName="ad_user_id")
	public User getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(User closedBy) {
		this.closedBy = closedBy;
	}
	@Column(name="closed_date")
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	
	@Column(name="purpose")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Column(name="returnby")
	public Date getReturnBy() {
		return returnBy;
	}
	
	public void setReturnBy(Date returnBy) {
		this.returnBy = returnBy;
	}
	
	@Column(name="vehicleno")
	public String getVehicleNo() {
		return vehicleNo;
	}
	
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	
	@Column(name="vehicletype")
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Column(name="transportername")
	public String getTransporterName() {
		return transporterName;
	}
	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}
	
	
	@Column(name="cancel_desc")
	public String getCancelDesc() {
		return cancelDesc;
	}
	public void setCancelDesc(String cancelDesc) {
		this.cancelDesc = cancelDesc;
	}
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="planthead",referencedColumnName="ad_user_id")
	public User getPlanthead() {
		return planthead;
	}
	public void setPlanthead(User planthead) {
		this.planthead = planthead;
	}
	
	
	@Column(name="planthead_date")
	public Date getPlantheadDate() {
		return plantheadDate;
	}
	
	public void setPlantheadDate(Date plantheadDate) {
		this.plantheadDate = plantheadDate;
	}
	
	
	
	
	
	
	
}
