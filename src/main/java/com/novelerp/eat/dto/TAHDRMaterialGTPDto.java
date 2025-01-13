/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.GtpParameterDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;

public class TAHDRMaterialGTPDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long tahdrMaterialGtpId;
	private String isPublish;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrMaterialId",scope=Long.class)
	private TAHDRMaterialDto tahdrMaterial;
	private GtpParameterDto gtp;
	private String isAdded;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrDetailId",scope=Long.class)
	private TAHDRDetailDto tahdrDetail;
	
	public Long getTahdrMaterialGtpId() {
		return tahdrMaterialGtpId;
	}
	public void setTahdrMaterialGtpId(Long tahdrMaterialGtpId) {
		this.tahdrMaterialGtpId = tahdrMaterialGtpId;
	}
	public String getIsPublish() {
		if(CommonUtil.isStringEmpty(isPublish)){
			return "N";
		}
		return isPublish;
	}
	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}
	public TAHDRMaterialDto getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterialDto tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	public GtpParameterDto getGtp() {
		return gtp;
	}
	public void setGtp(GtpParameterDto gtp) {
		this.gtp = gtp;
	}
	public String getIsAdded() {
		if(CommonUtil.isStringEmpty(isAdded)){
			return "N";
		}
		return isAdded;
	}
	public void setIsAdded(String isAdded) {
		this.isAdded = isAdded;
	}
	
	public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
}
