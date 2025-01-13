package com.novelerp.eat.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.core.util.DateUtil;

public class OtpDto implements Serializable{
	
	private String otp;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date otpGenerationDate;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date otpExpiryDate;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date otpSubmittedDate;
	
	private String isOtpSent;
	
	private String hasRegistered;
	
	private Long auctionId;
	private String mobileNo;
	

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtpGenerationDate() {
		return otpGenerationDate;
	}

	public void setOtpGenerationDate(Date otpGenerationDate) {
		this.otpGenerationDate = otpGenerationDate;
	}

	public Date getOtpExpiryDate() {
		return otpExpiryDate;
	}

	public void setOtpExpiryDate(Date otpExpiryDate) {
		
		this.otpExpiryDate = otpExpiryDate;
	}

	public String getIsOtpSent() {
		return isOtpSent;
	}

	public void setIsOtpSent(String isOtpSent) {
		this.isOtpSent = isOtpSent;
	}

	public Date getOtpSubmittedDate() {
		return otpSubmittedDate;
	}

	public void setOtpSubmittedDate(Date otpSubmittedDate) {
		this.otpSubmittedDate = otpSubmittedDate;
	}

	public String getHasRegistered() {
		return hasRegistered;
	}

	public void setHasRegistered(String hasRegistered) {
		this.hasRegistered = hasRegistered;
	}

	public Long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(Long auctionId) {
		this.auctionId = auctionId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	
	
	

}
