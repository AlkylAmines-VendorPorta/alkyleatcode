package com.novelerp.alkyl.dto;

public class QCFApproverSAPDto {
	private static final long serialVersionUID = 1L;
	private String srNumber;
	private String emailAddress;
	private String group;
	private String initial;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSrNumber() {
		return srNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getGroup() {
		return group;
	}
	public String getInitial() {
		return initial;
	}
	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	


}
