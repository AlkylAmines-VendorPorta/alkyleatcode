package com.novelerp.alkyl.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.core.util.DateUtil;

	

public class OutwardReadDto {
	private String salesOrderNoFrom;
	private String salesOrderNoTo;
	private String requestNoFrom;
	private String requestNoTo;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date requestDateFrom;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date requestDateTo;
	private String status;
	private String freightScope;
	private String plant;
	
	public Date getLastAsnDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(requestDateFrom);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartAsnDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(requestDateFrom);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	public Date getLastAsnDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(requestDateTo);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartAsnDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(requestDateTo);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
	public String getSalesOrderNoFrom() {
		return salesOrderNoFrom;
	}
	public void setSalesOrderNoFrom(String salesOrderNoFrom) {
		if (!salesOrderNoFrom.equals("")) {
		this.salesOrderNoFrom = salesOrderNoFrom;
		}
	}
	public String getSalesOrderNoTo() {
		return salesOrderNoTo;
	}
	public void setSalesOrderNoTo(String salesOrderNoTo) {
		if (!salesOrderNoTo.equals("")) {
		this.salesOrderNoTo = salesOrderNoTo;
		}
	}
	public String getRequestNoFrom() {
		return requestNoFrom;
	}
	public void setRequestNoFrom(String requestNoFrom) {
		if (!requestNoFrom.equals("")) {
		this.requestNoFrom = requestNoFrom;
		}
	}
	public String getRequestNoTo() {
		return requestNoTo;
	}
	public void setRequestNoTo(String requestNoTo) {
		if (!requestNoTo.equals("")) {
		this.requestNoTo = requestNoTo;
		}
	}
	public Date getRequestDateFrom() {
		return requestDateFrom;
	}
	public void setRequestDateFrom(Date requestDateFrom) {
		this.requestDateFrom = requestDateFrom;
	}
	public Date getRequestDateTo() {
		return requestDateTo;
	}
	public void setRequestDateTo(Date requestDateTo) {
		this.requestDateTo = requestDateTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFreightScope() {
		return freightScope;
	}
	public void setFreightScope(String freightScope) {
		this.freightScope = freightScope;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	
	
	@Override
	public String toString() {
		return "OutwardReadDto [salesOrderNoFrom=" + salesOrderNoFrom + ", salesOrderNoTo=" + salesOrderNoTo
				+ ", requestNoFrom=" + requestNoFrom + ", requestNoTo=" + requestNoTo + ", requestDateFrom="
				+ requestDateFrom + ", requestDateTo=" + requestDateTo + ", status=" + status + ", freightScope="
				+ freightScope + ", plant=" + plant + "]";
	}

	
	
	
}
