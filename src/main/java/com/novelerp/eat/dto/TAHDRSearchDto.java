package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

/**
 * 
 * @author varsha
 *
 */

public class TAHDRSearchDto {
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrId",scope=Long.class)
	private TAHDRDto tahdr;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrDetailId",scope=Long.class)
	private TAHDRDetailDto tahdrDetail;
	private TAHDRMaterialDto tahdrMaterial;
	private String locationType;
	private String role;
	
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	public TAHDRMaterialDto getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterialDto tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	/*private Long tahdrId;
	private String tahdrCode;
	private Long tahdrFees;
	public TAHDRReportDto(Long tahdrId,String tahdrCode,Long tahdrFees)
	{
		this.tahdrId=tahdrId;
		this.tahdrCode=tahdrCode;
	    this.tahdrFees=tahdrFees;
	}
	public Long getTahdrId() {
		return tahdrId;
	}
	public void setTahdrId(Long tahdrId) {
		this.tahdrId = tahdrId;
	}
	public String getTahdrCode() {
		return tahdrCode;
	}
	public void setTahdrCode(String tahdrCode) {
		this.tahdrCode = tahdrCode;
	}
	public Long getTahdrFees() {
		return tahdrFees;
	}
	public void setTahdrFees(Long tahdrFees) {
		this.tahdrFees = tahdrFees;
	}*/
	

}
