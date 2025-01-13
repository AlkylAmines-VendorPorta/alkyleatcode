package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.RoleDto;

public class InfraApprovalLevelDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;

	private Long infraApprovalLevelId;
	private RoleDto role;
	private DesignationDto designation;
	private Long level;
	private String statusCode;
	
	
	public Long getInfraApprovalLevelId() {
		return infraApprovalLevelId;
	}
	public void setInfraApprovalLevelId(Long infraApprovalLevelId) {
		this.infraApprovalLevelId = infraApprovalLevelId;
	}
	public RoleDto getRole() {
		return role;
	}
	public void setRole(RoleDto role) {
		this.role = role;
	}
	public DesignationDto getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationDto designation) {
		this.designation = designation;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}
