package com.novelerp.alkyl.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.core.util.DateUtil;

public class BidderFilterDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long enqNoFrom;
	private Long enqNoTo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date enqDateFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date enqDateTo;
	private String buyerCode;
	private String vendorCode;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date enqEndDateFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date enqEndDateTo;
	private String enqStatus;
	private String biiderStatus;
	public String VendorName;
	
	public Date getLastEnqDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(enqDateFrom);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartEnqDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(enqDateFrom);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	public Date getLastEnqDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(enqDateTo);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartEnqDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(enqDateTo);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public String getVendorName() {
		return VendorName;
	}
	public void setVendorName(String VendorName) {
		this.VendorName = VendorName;
	}
	public Long getEnqNoFrom() {
		return enqNoFrom;
	}
	public void setEnqNoFrom(Long enqNoFrom) {
		this.enqNoFrom = enqNoFrom;
	}
	public Long getEnqNoTo() {
		return enqNoTo;
	}
	public void setEnqNoTo(Long enqNoTo) {
		this.enqNoTo = enqNoTo;
	}
	public Date getEnqDateFrom() {
		return enqDateFrom;
	}
	public void setEnqDateFrom(Date enqDateFrom) {
		this.enqDateFrom = enqDateFrom;
	}
	public Date getEnqDateTo() {
		return enqDateTo;
	}
	public void setEnqDateTo(Date enqDateTo) {
		this.enqDateTo = enqDateTo;
	}
	
	public String getBuyerCode() {
		return buyerCode;
	}
	public void setBuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public Date getEnqEndDateFrom() {
		return enqEndDateFrom;
	}
	public void setEnqEndDateFrom(Date enqEndDateFrom) {
		this.enqEndDateFrom = enqEndDateFrom;
	}
	public Date getEnqEndDateTo() {
		return enqEndDateTo;
	}
	public void setEnqEndDateTo(Date enqEndDateTo) {
		this.enqEndDateTo = enqEndDateTo;
	}
	public String getEnqStatus() {
		return enqStatus;
	}
	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}
	public String getBiiderStatus() {
		return biiderStatus;
	}
	public void setBiiderStatus(String biiderStatus) {
		this.biiderStatus = biiderStatus;
	}
	
	
}
