package com.novelerp.alkyl.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import com.novelerp.core.util.DateUtil;

public class ASNReadDto {
	private String poNoFrom;
	private String poNoTo;
	private Integer asnNoFrom;
	private Integer asnNoTo;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date asnDateFrom;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date asnDateTo;
	private Boolean isPONoFilter = false;
	private Boolean isAsnNoFilter = false;
	private Boolean isAsnDateFilter = false;
	private String vendorCode;
	private String status;
	private String requestedBy;
	private String plant;
	private String materialCode;
	private String vendorCodeTo;
	private String itemCodeFrom;
	private String itemCodeTo;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateInDateFrom;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date gateInDateTo;
	

	
    
	public Date getAsnDateFrom() {
		return asnDateFrom;
	}
	
	public Date getLastAsnDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(asnDateFrom);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartAsnDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(asnDateFrom);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	public Date getLastAsnDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(asnDateTo);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartAsnDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(asnDateTo);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	public Date getLastgateInDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(gateInDateFrom);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartgateInDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(gateInDateFrom);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	public Date getLastgateInDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(gateInDateTo);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartgateInDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(gateInDateTo);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public Date getAsnDateTo() {
		return asnDateTo;
	}

	public Boolean getIsAsnDateFilter() {
		return isAsnDateFilter;
	}

	public void setAsnDateFrom(Date asnDateFrom) {
		this.asnDateFrom = asnDateFrom;
	}

	public void setAsnDateTo(Date asnDateTo) {
		this.asnDateTo = asnDateTo;
	}

	public void setIsAsnDateFilter(Boolean isAsnDateFilter) {
		this.isAsnDateFilter = isAsnDateFilter;
	}

	public Boolean getIsPONoFilter() {
		return isPONoFilter;
	}

	public Boolean getIsAsnNoFilter() {
		return isAsnNoFilter;
	}

	public void setIsPONoFilter(Boolean isPONoFilter) {
		this.isPONoFilter = isPONoFilter;
	}

	public void setIsAsnNoFilter(Boolean isAsnNoFilter) {
		this.isAsnNoFilter = isAsnNoFilter;
	}

	public String getPoNoFrom() {
		return poNoFrom;
	}

	public String getPoNoTo() {
		return poNoTo;
	}

	public Integer getAsnNoFrom() {
		return asnNoFrom;
	}

	public Integer getAsnNoTo() {
		return asnNoTo;
	}

	public void setPoNoFrom(String poNoFrom) {
		if (!poNoFrom.equals("")) {
			this.poNoFrom = poNoFrom;
		}
	}

	public void setPoNoTo(String poNoTo) {
		if (!poNoTo.equals("")) {
			this.poNoTo = poNoTo;
		}
	}

	public void setAsnNoFrom(Integer asnNoFrom) {
		this.asnNoFrom = asnNoFrom;
	}

	public void setAsnNoTo(Integer asnNoTo) {
		this.asnNoTo = asnNoTo;
	}
	
	

	public String getVendorCode() {
		return vendorCode;
	}

	public String getStatus() {
		return status;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public String getPlant() {
		return plant;
	}

	public void setVendorCode(String vendorCode) {
		if (!vendorCode.equals("")) {
		this.vendorCode = vendorCode;
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	
	

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	
	
	public String getVendorCodeTo() {
		return vendorCodeTo;
	}

	public void setVendorCodeTo(String vendorCodeTo) {
               if(!vendorCodeTo.equals("")) {
		       this.vendorCodeTo = vendorCodeTo;
           }
	}
	
	

	public String getItemCodeFrom() {
		return itemCodeFrom;
	}

	public String getItemCodeTo() {
		return itemCodeTo;
	}

	public void setItemCodeFrom(String itemCodeFrom) {
		if(!itemCodeFrom.equals("")) {
		this.itemCodeFrom = itemCodeFrom;
		}
	}

	public void setItemCodeTo(String itemCodeTo) {
		if(!itemCodeTo.equals("")) {
		this.itemCodeTo = itemCodeTo;
		}
	}
	

	public Date getGateInDateFrom() {
		return gateInDateFrom;
	}

	public Date getGateInDateTo() {
		return gateInDateTo;
	}

	public void setGateInDateFrom(Date gateInDateFrom) {
		
		this.gateInDateFrom = gateInDateFrom;
	        
	}

	public void setGateInDateTo(Date gateInDateTo) {
		
		this.gateInDateTo = gateInDateTo;
		
	}

	@Override
	public String toString() {
		return "ASNReadDto [poNoFrom=" + poNoFrom + ", poNoTo=" + poNoTo + ", asnNoFrom=" + asnNoFrom + ", asnNoTo="
				+ asnNoTo + ", asnDateFrom=" + asnDateFrom + ", asnDateTo=" + asnDateTo + ", isPONoFilter="
				+ isPONoFilter + ", isAsnNoFilter=" + isAsnNoFilter + ", isAsnDateFilter=" + isAsnDateFilter
				+ ", vendorCode=" + vendorCode + ", status=" + status + ", requestedBy=" + requestedBy + ", plant="
				+ plant + ", materialCode=" + materialCode + ", vendorCodeTo=" + vendorCodeTo + ", itemCodeFrom="
				+ itemCodeFrom + ", itemCodeTo=" + itemCodeTo + ", gateInDateFrom=" + gateInDateFrom + ", gateInDateTo="
				+ gateInDateTo + "]";
	}

	
}
