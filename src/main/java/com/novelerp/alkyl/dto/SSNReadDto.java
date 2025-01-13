package com.novelerp.alkyl.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.core.util.DateUtil;

public class SSNReadDto {
	
	private String poNoFrom;
	private String poNoTo;
	private String ssnNoFrom;
	private String ssnNoTo;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date ssnDateFrom;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date ssnDateTo;
	private Boolean isPONoFilter = false;
	private Boolean isSsnNoFilter = false;
	private Boolean isSsnDateFilter = false;
	private String vendorCode;
	private String status;
	private String requestedBy;
	private String plant;
	public String getPoNoFrom() {
		return poNoFrom;
	}
	public String getPoNoTo() {
		return poNoTo;
	}
	
	public Date getSsnDateFrom() {
		return ssnDateFrom;
	}
	
	public Date getLastSsnDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(ssnDateFrom);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartSsnDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(ssnDateFrom);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	public Date getLastSsnDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(ssnDateTo);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartSsnDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(ssnDateTo);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public Date getSsnDateTo() {
		return ssnDateTo;
	}
	public Boolean getIsPONoFilter() {
		return isPONoFilter;
	}
	public Boolean getIsSsnNoFilter() {
		return isSsnNoFilter;
	}
	public Boolean getIsSsnDateFilter() {
		return isSsnDateFilter;
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
	
	public void setSsnDateFrom(Date ssnDateFrom) {
		this.ssnDateFrom = ssnDateFrom;
	}
	public void setSsnDateTo(Date ssnDateTo) {
		this.ssnDateTo = ssnDateTo;
	}
	public void setIsPONoFilter(Boolean isPONoFilter) {
		this.isPONoFilter = isPONoFilter;
	}
	public void setIsSsnNoFilter(Boolean isSsnNoFilter) {
		this.isSsnNoFilter = isSsnNoFilter;
	}
	public void setIsSsnDateFilter(Boolean isSsnDateFilter) {
		this.isSsnDateFilter = isSsnDateFilter;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
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
	@Override
	public String toString() {
		return "SSNReadDto [poNoFrom=" + poNoFrom + ", poNoTo=" + poNoTo + ", ssnNoFrom=" + ssnNoFrom + ", ssnNoTo="
				+ ssnNoTo + ", ssnDateFrom=" + ssnDateFrom + ", ssnDateTo=" + ssnDateTo + ", isPONoFilter="
				+ isPONoFilter + ", isSsnNoFilter=" + isSsnNoFilter + ", isSsnDateFilter=" + isSsnDateFilter
				+ ", vendorCode=" + vendorCode + ", status=" + status + ", requestedBy=" + requestedBy + ", plant="
				+ plant + "]";
	}
	public String getSsnNoFrom() {
		return ssnNoFrom;
	}
	public String getSsnNoTo() {
		return ssnNoTo;
	}
	public void setSsnNoFrom(String ssnNoFrom) {
		if (!ssnNoFrom.equals("")) {
			this.ssnNoFrom = ssnNoFrom;
		}
		
		
	}
	public void setSsnNoTo(String ssnNoTo) {
		if (!ssnNoTo.equals("")) {
			this.ssnNoTo = ssnNoTo;
		}
		
	}	

	
}
