package com.novelerp.appbase.master.dto;

public class MaterialSearchDto {
	
	private String name;
	private String code;
	private Long materialGroupId;
	private Long materialSubGroupId;
	private Long hsnCode;
	private String typeCode;
	private Long partnerOrgId;
	private Long bPartnerId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getMaterialGroupId() {
		return materialGroupId;
	}
	public void setMaterialGroupId(Long materialGroupId) {
		this.materialGroupId = materialGroupId;
	}
	public Long getMaterialSubGroupId() {
		return materialSubGroupId;
	}
	public void setMaterialSubGroupId(Long materialSubGroupId) {
		this.materialSubGroupId = materialSubGroupId;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Long getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(Long hsnCode) {
		this.hsnCode = hsnCode;
	}
	public Long getPartnerOrgId() {
		return partnerOrgId;
	}
	public void setPartnerOrgId(Long partnerOrgId) {
		this.partnerOrgId = partnerOrgId;
	}
	public Long getbPartnerId() {
		return bPartnerId;
	}
	public void setbPartnerId(Long bPartnerId) {
		this.bPartnerId = bPartnerId;
	}
	

}
