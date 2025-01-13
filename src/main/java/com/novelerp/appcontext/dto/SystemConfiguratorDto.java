package com.novelerp.appcontext.dto;

import com.novelerp.appbase.master.dto.ReferenceListDto;

public class SystemConfiguratorDto extends CommonContextDto {
	
	private static final long serialVersionUID = 1L;
	private Long systemConfiguratorId;
	private String configType;
	private String name;
	private String code;
	private String value;
	private String description;
    private String referenceCode;
    private String isSystemConfig;
    private String isSystemAdmin;
    private ReferenceListDto ReferenceList;
	
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getSystemConfiguratorId() {
		return systemConfiguratorId;
	}
	public void setSystemConfiguratorId(Long systemConfiguratorId) {
		this.systemConfiguratorId = systemConfiguratorId;
	}
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public String getIsSystemConfig() {
		return isSystemConfig;
	}
	public void setIsSystemConfig(String isSystemConfig) {
		this.isSystemConfig = isSystemConfig;
	}
	public ReferenceListDto getReferenceList() {
		return ReferenceList;
	}
	public void setReferenceList(ReferenceListDto referenceList) {
		ReferenceList = referenceList;
	}
	public String getIsSystemAdmin() {
		return isSystemAdmin;
	}
	public void setIsSystemAdmin(String isSystemAdmin) {
		this.isSystemAdmin = isSystemAdmin;
	}
	
	
}
