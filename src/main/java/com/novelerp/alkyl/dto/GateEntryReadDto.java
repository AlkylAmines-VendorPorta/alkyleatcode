package com.novelerp.alkyl.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.core.util.DateUtil;

public class GateEntryReadDto {
	private static final long serialVersionUID = 1L;
	private String reqNoFrom;
	private String reqNoTo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date reqDateFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date reqDateTo;
	private Boolean isreqNoFilter = false;
	private String status;
	private String plant;
	private String docType;
	
	
	public String getReqNoFrom() {
		return reqNoFrom;
	}
	public void setReqNoFrom(String reqNoFrom) {
		if (!reqNoFrom.equals("")) {
		this.reqNoFrom = reqNoFrom;
		}
	}
	public String getReqNoTo() {
		return reqNoTo;
	}
	public void setReqNoTo(String reqNoTo) {
		if (!reqNoTo.equals("")) {
		this.reqNoTo = reqNoTo;
		}
	}
	public Date getReqDateFrom() {
		return reqDateFrom;
	}
	public void setReqDateFrom(Date reqDateFrom) {
		this.reqDateFrom = reqDateFrom;
	}
	public Date getReqDateTo() {
		return reqDateTo;
	}
	public void setReqDateTo(Date reqDateTo) {
		this.reqDateTo = reqDateTo;
	}
	
	public Date getLastReqDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(reqDateFrom);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartReqDateFrom() {
		 LocalDateTime localDateTime = dateToLocalDateTime(reqDateFrom);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	public Date getLastReqDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(reqDateTo);
		    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		    return localDateTimeToDate(endOfDay);
	}
	
	public Date getStartReqDateTo() {
		 LocalDateTime localDateTime = dateToLocalDateTime(reqDateTo);
		    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		    return localDateTimeToDate(startOfDay);
	}
	
	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if (!status.equals("")) {
		this.status = status;
		}
	}
	
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		if (!plant.equals("")) {
		this.plant = plant;
		}
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		if (!docType.equals("")) {
		this.docType = docType;
		}
	}
}
