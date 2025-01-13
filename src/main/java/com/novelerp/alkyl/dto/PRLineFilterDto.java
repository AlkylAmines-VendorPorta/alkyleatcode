package com.novelerp.alkyl.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.core.util.DateUtil;

public class PRLineFilterDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String plant;
	private Long userId;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date prDateFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date prDateTo;
	private String purchaseGroupFrom;
	private String purchaseGroupTo;
	private String status;
	private String prNoFrom;
	private String prNoTo;
	
	
	public Date getLastprDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(prDateFrom);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartprDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(prDateFrom);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	public Date getLastprDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(prDateTo);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartprDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(prDateTo);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
//	public Date getFromDate() {
//		return fromDate;
//	}
//	public void setFromDate(Date fromDate) {
//		this.fromDate = fromDate;
//	}
//	public Date getToDate() {
//		return toDate;
//	}
//	public void setToDate(Date toDate) {
//		this.toDate = toDate;
//	}
	public Date getPrDateFrom() {
		return prDateFrom;
	}
	public Date getPrDateTo() {
		return prDateTo;
	}
	public void setPrDateFrom(Date prDateFrom) {
		this.prDateFrom = prDateFrom;
	}
	public void setPrDateTo(Date prDateTo) {
		this.prDateTo = prDateTo;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPurchaseGroupFrom() {
		return purchaseGroupFrom;
	}

	public String getPurchaseGroupTo() {
		return purchaseGroupTo;
	}

	public void setPurchaseGroupFrom(String purchaseGroupFrom) {
		this.purchaseGroupFrom = purchaseGroupFrom;
	}

	public void setPurchaseGroupTo(String purchaseGroupTo) {
		this.purchaseGroupTo = purchaseGroupTo;
	}

	public String getPrNoFrom() {
		return prNoFrom;
	}

	public String getPrNoTo() {
		return prNoTo;
	}

	public void setPrNoFrom(String prNoFrom) {
		this.prNoFrom = prNoFrom;
	}

	public void setPrNoTo(String prNoTo) {
		this.prNoTo = prNoTo;
	}
	
	
	
	
	
	
	
}
