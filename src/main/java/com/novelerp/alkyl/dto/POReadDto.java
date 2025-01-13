package com.novelerp.alkyl.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.core.util.DateUtil;

public class POReadDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String poNoFrom;
	private String poNoTo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date poDateFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date poDateTo;
	private String vendorCodeFrom;
	private String vendorCodeTo;
	private String empCode;
	private String vendorCode;
	private Boolean isPONoFilter=false;
	private Boolean isPODateFilter=false;
	private Boolean isVendorFilter=false;
	private Boolean isEmpFilter=false;
	private Long bPartnerId;
	private String outboundDeliveryNo;
	public String getPoNoFrom() {
		return poNoFrom;
	}
	public void setPoNoFrom(String poNoFrom) {
		this.poNoFrom = poNoFrom;
	}
	public String getPoNoTo() {
		return poNoTo;
	}
	public void setPoNoTo(String poNoTo) {
		this.poNoTo = poNoTo;
	}
	public Date getPoDateFrom() {
		return poDateFrom;
	}
	public void setPoDateFrom(Date poDateFrom) {
		this.poDateFrom = poDateFrom;
	}
	public Date getPoDateTo() {
		return poDateTo;
	}
	public void setPoDateTo(Date poDateTo) {
		this.poDateTo = poDateTo;
	}
	public String getVendorCodeFrom() {
		return vendorCodeFrom;
	}
	public void setVendorCodeFrom(String vendorCodeFrom) {
		this.vendorCodeFrom = vendorCodeFrom;
	}
	public String getVendorCodeTo() {
		return vendorCodeTo;
	}
	public void setVendorCodeTo(String vendorCodeTo) {
		this.vendorCodeTo = vendorCodeTo;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public Boolean getIsPONoFilter() {
		return isPONoFilter;
	}
	public void setIsPONoFilter(Boolean isPONoFilter) {
		this.isPONoFilter = isPONoFilter;
	}
	public Boolean getIsPODateFilter() {
		return isPODateFilter;
	}
	public void setIsPODateFilter(Boolean isPODateFilter) {
		this.isPODateFilter = isPODateFilter;
	}
	public Boolean getIsVendorFilter() {
		return isVendorFilter;
	}
	public void setIsVendorFilter(Boolean isVendorFilter) {
		this.isVendorFilter = isVendorFilter;
	}
	public Boolean getIsEmpFilter() {
		return isEmpFilter;
	}
	public void setIsEmpFilter(Boolean isEmpFilter) {
		this.isEmpFilter = isEmpFilter;
	}
	public Long getbPartnerId() {
		return bPartnerId;
	}
	public void setbPartnerId(Long bPartnerId) {
		this.bPartnerId = bPartnerId;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
	public String getOutboundDeliveryNo() {
		return outboundDeliveryNo;
	}
	public void setOutboundDeliveryNo(String outboundDeliveryNo) {
		this.outboundDeliveryNo = outboundDeliveryNo;
	}
	@Override
	public String toString() {
		return "POReadDto [poNoFrom=" + poNoFrom + ", poNoTo=" + poNoTo + ", poDateFrom=" + poDateFrom + ", poDateTo="
				+ poDateTo + ", vendorCodeFrom=" + vendorCodeFrom + ", vendorCodeTo=" + vendorCodeTo + ", empCode="
				+ empCode + ", vendorCode=" + vendorCode + ", isPONoFilter=" + isPONoFilter + ", isPODateFilter="
				+ isPODateFilter + ", isVendorFilter=" + isVendorFilter + ", isEmpFilter=" + isEmpFilter
				+ ", bPartnerId=" + bPartnerId + ", outboundDeliveryNo=" + outboundDeliveryNo + "]";
	}

}
