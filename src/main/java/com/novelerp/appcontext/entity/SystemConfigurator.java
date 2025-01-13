package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.ReferenceList;

@Entity
@Table(name="ad_sysconfig")
public class SystemConfigurator extends ContextPO {
	/**
	 * 
	 */
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
	private ReferenceList ReferenceList;
	
	@Id
	@SequenceGenerator(name="ad_sysconfig_seq",sequenceName="ad_sysconfig_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="ad_sysconfig_seq")	
	@Column(name = "ad_sysconfig_id", updatable=false)
	public Long getSystemConfiguratorId() {
		return systemConfiguratorId;
	}
	public void setSystemConfiguratorId(Long systemConfiguratorId) {
		this.systemConfiguratorId = systemConfiguratorId;
	}
	
	@Column(name="config_type")
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name="reference_code")
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	@Column(name="server_admin")
	public String getIsSystemConfig() {
		return isSystemConfig;
	}
	public void setIsSystemConfig(String isSystemConfig) {
		this.isSystemConfig = isSystemConfig;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_reference_list_id")
	public ReferenceList getReferenceList() {
		return ReferenceList;
	}
	public void setReferenceList(ReferenceList referenceList) {
		ReferenceList = referenceList;
	}
	@Column(name="system_admin")
	public String getIsSystemAdmin() {
		return isSystemAdmin;
	}
	public void setIsSystemAdmin(String isSystemAdmin) {
		this.isSystemAdmin = isSystemAdmin;
	}
	
}
