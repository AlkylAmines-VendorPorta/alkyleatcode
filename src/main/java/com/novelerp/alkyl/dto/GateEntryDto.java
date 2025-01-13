package com.novelerp.alkyl.dto;

import java.util.Date;
import java.util.List;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;

public class GateEntryDto  extends CommonContextDto{
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
	private List<GateEntryLineDto> gateEntryLineList;
	private String plant;
	private UserDto hod;
	private Date hodDate;
	private UserDto fh;
	private Date fhDate;
	private UserDto commercial;
	private Date commercialDate;
	private UserDto gateOut;
	private Date gateOutDate;
	private UserDto closedBy;
	private Date closedDate;
	private String purpose;
	private Date returnBy;
	private String vehicleNo;
	private String vehicleType;
	private String transporterName;
    private UserDto createdBy;
    private String cancelDesc;
    private UserDto planthead;
	private Date plantheadDate;


	
	public UserDto getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}
	public String getPurpose() {
		return purpose;
	}
	public Date getReturnBy() {
		return returnBy;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public String getTransporterName() {
		return transporterName;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setReturnBy(Date returnBy) {
		this.returnBy = returnBy;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}
	

	public Long getGateEntryId() {
		return gateEntryId;
	}
	public void setGateEntryId(Long gateEntryId) {
		this.gateEntryId = gateEntryId;
	}
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getRejectDesc() {
		return rejectDesc;
	}
	public void setRejectDesc(String rejectDesc) {
		this.rejectDesc = rejectDesc;
	}
	public List<GateEntryLineDto> getGateEntryLineList() {
		return gateEntryLineList;
	}
	public void setGateEntryLineList(List<GateEntryLineDto> gateEntryLineList) {
		this.gateEntryLineList = gateEntryLineList;
	}
	public String getHodRejectDesc() {
		return hodRejectDesc;
	}
	public void setHodRejectDesc(String hodRejectDesc) {
		this.hodRejectDesc = hodRejectDesc;
	}
	public String getCommRejectDesc() {
		return commRejectDesc;
	}
	public void setCommRejectDesc(String commRejectDesc) {
		this.commRejectDesc = commRejectDesc;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public UserDto getHod() {
		return hod;
	}
	public void setHod(UserDto hod) {
		this.hod = hod;
	}
	public Date getHodDate() {
		return hodDate;
	}
	public void setHodDate(Date hodDate) {
		this.hodDate = hodDate;
	}
	public UserDto getFh() {
		return fh;
	}
	public void setFh(UserDto fh) {
		this.fh = fh;
	}
	public Date getFhDate() {
		return fhDate;
	}
	public void setFhDate(Date fhDate) {
		this.fhDate = fhDate;
	}
	public UserDto getCommercial() {
		return commercial;
	}
	public void setCommercial(UserDto commercial) {
		this.commercial = commercial;
	}
	public Date getCommercialDate() {
		return commercialDate;
	}
	public void setCommercialDate(Date commercialDate) {
		this.commercialDate = commercialDate;
	}
	public UserDto getGateOut() {
		return gateOut;
	}
	public void setGateOut(UserDto gateOut) {
		this.gateOut = gateOut;
	}
	public Date getGateOutDate() {
		return gateOutDate;
	}
	public void setGateOutDate(Date gateOutDate) {
		this.gateOutDate = gateOutDate;
	}
	public UserDto getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(UserDto closedBy) {
		this.closedBy = closedBy;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getVendorCode() {
//		return vendorCode;
//	}
//	public void setVendorCode(String vendorCode) {
//		this.vendorCode = vendorCode;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
	public String getCancelDesc() {
		return cancelDesc;
	}
	public void setCancelDesc(String cancelDesc) {
		this.cancelDesc = cancelDesc;
	}
	
	
	public UserDto getPlanthead() {
		return planthead;
	}
	public void setPlanthead(UserDto planthead) {
		this.planthead = planthead;
	}
	
	public Date getPlantheadDate() {
		return plantheadDate;
	}
	
	public void setPlantheadDate(Date plantheadDate) {
		this.plantheadDate = plantheadDate;
	}

	
	
	
	
	
}
